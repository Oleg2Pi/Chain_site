package by.polikarpov.controller;

import by.polikarpov.entity.Person;
import by.polikarpov.entity.Work;
import by.polikarpov.service.PersonService;
import by.polikarpov.service.WorkService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Controller
public class SaveWorkController {

    private final PersonService personService;
    private final WorkService workService;

    public SaveWorkController(PersonService personService, WorkService workService) {
        this.personService = personService;
        this.workService = workService;
    }

    @PostMapping("create_work/{chatId}")
    public String saveWork(@PathVariable("chatId") Long chatId,
                           @RequestParam("file") MultipartFile file,
                           @RequestParam("projectName") String projectName,
                           @RequestParam("description") String description
    ) throws IOException {
        Person person = personService.getPersonByChatId(chatId).orElse(null);

        if (person == null) {
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

        return "redirect:/" + chatId + "/profile/portfolio";
    }

    private String download(MultipartFile file, Integer works) throws IOException {
        String fileOriginal = file.getOriginalFilename();
        String fileExtension = fileOriginal != null ? fileOriginal.substring(fileOriginal.lastIndexOf('.')) : "";
        String fileName = works + fileExtension;
        String filePath = null;

        String UPLOAD_DIR = "src/main/resources/static/upload_files/";
        if (Objects.requireNonNull(file.getContentType()).startsWith("image")) {
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
