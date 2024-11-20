package by.polikarpov.controller;

import by.polikarpov.entity.*;
import by.polikarpov.service.ExecutorService;
import by.polikarpov.service.PersonService;
import by.polikarpov.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Controller
public class ProfileController {

    @Autowired
    private PersonService personService;

    @Autowired
    private WorkService workService;

    private String UPLOAD_DIR = "src/main/resources/static/upload_files/";

    @GetMapping("/{chatId}/main")
    public String mainSite(@PathVariable("chatId") long chatId, Model model) {
        model.addAttribute("chatId", chatId);

        return "main";
    }

    @GetMapping("/{chatId}/profile")
    public String profile(@PathVariable("chatId") long chatId, Model model) {
        Person person = personService.getPersonByChatId(chatId).orElse(null);

        if (person == null) {
            return "error";
        }

        ImagePerson imagePerson = person.getImage();
        Resume resume = person.getExecutor().getResume();
//        List<Work> works = workService.getTopTenWorks(person.getExecutor().getId());
        List<Work> works = person.getExecutor().getWorks();

        String image = imagePerson.getFilePath();
        String activityArea = null;
        String workExperience = null;

        if (resume != null) {
            activityArea = resume.getActivityArea().getType();
            workExperience = resume.getWorkExperience().getCategory();
        }

        model.addAttribute("person", person);
        model.addAttribute("image", image);
        model.addAttribute("activityArea", activityArea);
        model.addAttribute("workExperience", workExperience);
        model.addAttribute("works", works);

        return "executor_profile/profile";
    }

    @GetMapping("/{chatId}/resume")
    public String resume(@PathVariable("chatId") Long chatId, Model model) {
        Person person = personService.getPersonByChatId(chatId).orElse(null);

        if (person == null) {
            return "error";
        }

        Executor executor = person.getExecutor();
        Resume resume = executor.getResume();

        if (resume == null) {
            model.addAttribute("resume", null);
            return "executor_profile/resume/create";
        }

        model.addAttribute("resume", "not null");
        model.addAttribute("activityArea", resume.getActivityArea().getType());
        model.addAttribute("workExperience", resume.getWorkExperience().getCategory());
        model.addAttribute("userStatus", resume.getUserStatus().getCategory());
        model.addAttribute("informationYourself", resume.getInformationYourself());
        return "executor_profile/resume/change";
    }

    @GetMapping("/{chatId}/portfolio")
    public String portfolio(@PathVariable("chatId") Long chatId, Model model) {
        return "executor_portfolio/portfolio";
    }

    @GetMapping("/{chatId}/portfolio/create")
    public String createWork(@PathVariable("chatId") Long chatId, Model model) {
        model.addAttribute("idPerson", chatId);
        return "executor_portfolio/work/create_work";
    }

    @PostMapping("/{chatId}/portfolio")
    public String saveWork(@PathVariable("chatId") Long chatId,
                           @RequestParam("file") MultipartFile file,
                           @RequestParam("projectName") String projectName,
                           @RequestParam("description") String description,
                           Model model) throws IOException {
        Person person = personService.getPersonByChatId(chatId).orElse(null);

        if (person == null && person.getExecutor() == null) {
            return "error";
        }

        int works = workService.getCounterWorks();

        Work work = Work.builder()
                .executor(person.getExecutor())
                .name(projectName)
                .dateAdded(Timestamp.valueOf(LocalDateTime.now()))
                .description(description)
                .file(download(file, works))
                .type(file.getContentType())
                .build();

        workService.saveWork(work);

        return "redirect:/profile/" + chatId + "/portfolio";
    }

    private String download(MultipartFile file, Integer works) throws IOException {
        String fileOriginal = file.getOriginalFilename();
        String fileExtension = fileOriginal != null ? fileOriginal.substring(fileOriginal.lastIndexOf('.')) : "";
        String fileName = works + fileExtension;
        String filePath = null;

        if (file.getContentType().startsWith("image")) {
            filePath = Paths.get(UPLOAD_DIR + "images_of_work/", fileName).toString();
        } else if (file.getContentType().startsWith("video")) {
            fileName = fileName.replace(fileExtension, ".mp4");
            filePath = Paths.get(UPLOAD_DIR + "videos_of_work/", fileName).toString();
        }

        if (filePath != null) {
            File destinationalFile = new File(filePath);

            try (InputStream inputStream = file.getInputStream();
                 OutputStream outputStream = new FileOutputStream(destinationalFile)) {

                byte[] buffer = new byte[65536];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.flush();
            }
        }

        String path = null;
        if (filePath != null) {
            path = filePath.substring(filePath.lastIndexOf("\\upload_files"));
        }

        return path;
    }
}
