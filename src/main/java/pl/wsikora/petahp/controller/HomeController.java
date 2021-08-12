package pl.wsikora.petahp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String goToMain() {
        return "home/index";
    }

    @GetMapping("/przejdz-do-ankiety")
    public String goToSurvey() {
        return "home/go_to_form";
    }

    @GetMapping("/logowanie")
    public String logIn() {
        return "home/login";
    }

}
