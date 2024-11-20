package by.polikarpov.controller;

import by.polikarpov.entity.*;
import by.polikarpov.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/{chatId}")
public class ProfileController {

    private final PersonService personService;

    public ProfileController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/dev")
    public String dev(@PathVariable("chatId") long chatId, Model model) {
        model.addAttribute("chatId", chatId);

        return "dev";
    }

    @GetMapping("/main")
    public String mainSite(@PathVariable("chatId") long chatId, Model model) {
        model.addAttribute("chatId", chatId);

        return "main";
    }

    @GetMapping("/main/{personId}")
    public String showPerson(@PathVariable("chatId") String chatId,
                             @PathVariable("personId") String personId, Model model) {
        model.addAttribute("chatId", chatId);

        return "dev";
    }

    @GetMapping("/profile")
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

    @GetMapping("/profile/resume")
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

    @GetMapping("/profile/portfolio")
    public String portfolio(@PathVariable("chatId") String chatId, Model model) {
        model.addAttribute("chatId", chatId);
        return "executor_portfolio/portfolio";
    }

    @GetMapping("/profile/portfolio/create")
    public String createWork(@PathVariable("chatId") Long chatId, Model model) {
        model.addAttribute("chatId", chatId);
        return "executor_portfolio/work/create_work";
    }

}
