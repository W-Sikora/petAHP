package pl.wsikora.petahp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.wsikora.petahp.model.entities.User;
import pl.wsikora.petahp.model.repositories.UserRepository;


@Controller
@RequestMapping("")
public class HomeController {
    private UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("")
    public String homeAction() {
        return "index";
    }

    @RequestMapping("/rejestracja")
    public String registrationAction(Model model) {
        model.addAttribute("user", new User());
        return "/login&registration/registration";
    }

    @RequestMapping("rejestrowanie")
    public String registerAction(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/logowanie";
    }

    @RequestMapping("/logowanie")
    public String loginAction() {
        return "/login&registration/login";
    }

    @RequestMapping("/zalogowanie")
    public String logInAction() {
        return "redirect:/panel";
    }

}

