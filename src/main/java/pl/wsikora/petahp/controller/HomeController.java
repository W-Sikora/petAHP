package pl.wsikora.petahp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.wsikora.petahp.model.entities.User;
import pl.wsikora.petahp.model.repositories.UserRepo;


import java.util.Map;


@Controller
public class HomeController {

    private UserRepo userRepo;

    public HomeController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @RequestMapping(value = "/")
    public String homeAction() {
        return "home/index";
    }

    @RequestMapping(value = "/rejestracja")
    public String register() {
        return "home/registration";
    }

    @RequestMapping(value = "/rejestrowanie")
    public String registerCheck(@RequestParam Map<String, String> registrationData, Model model) {
        if (userRepo.findUserByEmail(registrationData.get("email")) == null) {
            User user = new User();
            user.setName(registrationData.get("name"));
            user.setEmail(registrationData.get("email"));
            user.setPassword(registrationData.get("password"));
            userRepo.save(user);
            return "redirect:logowanie";
        } else {
            model.addAttribute("error", "Podany adres email jest już zajęty");
            return "home/registration";
        }
    }

    @RequestMapping(value = "/logowanie")
    public String login() {
        return "home/login";
    }

    @RequestMapping(value = "/przejdz-do-ankiety")
    public String goToForm() {
        return "home/go_to_form";
    }
}
