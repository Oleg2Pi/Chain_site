package by.polikarpov.controller;

import by.polikarpov.entity.*;
import by.polikarpov.service.PersonService;
import by.polikarpov.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Base64;
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
}
