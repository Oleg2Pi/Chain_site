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

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private PersonService personService;

    @Autowired
    private WorkService workService;

    @GetMapping("/{id}")
    public String profile(@PathVariable("id") int id, Model model) {
        Person person = personService.getPersonById(id).orElse(null);

        if (person == null) {
            return "error";
        }

        ImagePerson imagePerson = person.getImage();
        Resume resume = person.getExecutor().getResume();
        List<Work> works = person.getExecutor().getWorks();

        String image = imagePerson.getBase64Data();
        String activityArea = null;
        String workExperience = null;

        if (resume != null) {
            activityArea = resume.getActivityArea().getType();
            workExperience = resume.getWorkExperience().getCategory();
        }

        if (works != null && !works.isEmpty()) {
            model.addAttribute("works", works);
        } else {
            model.addAttribute("works", null);
        }

        model.addAttribute("person", person);
        model.addAttribute("image", image);
        model.addAttribute("activityArea", activityArea);
        model.addAttribute("workExperience", workExperience);

        return "executor_profile/profile";
    }

    @GetMapping("/{id}/resume")
    public String resume(@PathVariable("id") int id, Model model) {
        Person person = personService.getPersonById(id).orElse(null);

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

    @GetMapping("/{id}/portfolio")
    public String portfolio(@PathVariable("id") int id, Model model) {
        return "executor_portfolio/portfolio";
    }

    @GetMapping("/{id}/portfolio/create")
    public String createWork(@PathVariable("id") int id, Model model) {
        model.addAttribute("idPerson", id);
        return "executor_portfolio/work/create_work";
    }

    @PostMapping("/{id}/portfolio")
    public String saveWork(@PathVariable("id") int id,
                           @RequestParam("file") MultipartFile file,
                           @RequestParam("projectName") String projectName,
                           @RequestParam("description") String description,
                           Model model) throws IOException {
        Person person = personService.getPersonById(id).orElse(null);

        if (person == null && person.getExecutor() == null) {
            return "error";
        }

        Work work = Work.builder()
                .executor(person.getExecutor())
                .name(projectName)
                .dateAdded(Timestamp.valueOf(LocalDateTime.now()))
                .description(description)
                .file(file.getBytes())
                .type(file.getContentType())
                .build();

        workService.saveWork(work);
        System.out.println(work.toString());

        return "redirect:/profile/" + id + "/portfolio";
    }
}
