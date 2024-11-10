package by.polikarpov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorizationController {

    @GetMapping("/authorization")
    public String authorization() {
        return "authorization/authorization";
    }
}
