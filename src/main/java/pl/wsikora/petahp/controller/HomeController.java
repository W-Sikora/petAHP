package pl.wsikora.petahp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.wsikora.petahp.model.entities.User;
import pl.wsikora.petahp.model.repositories.UserRepo;


@Controller
public class HomeController {
    private UserRepo userRepo;

    public HomeController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @RequestMapping("")
    public String goToMain() {
        return "home/index";
    }

    @RequestMapping("/rejestracja")
    public String register() {
        return "home/registration";
    }

    @RequestMapping("/logowanie")
    public String logIn() {
        return "home/login";
    }

    @RequestMapping("/przejdz-do-ankiety")
    public String goToSurvey() {
        return "home/go_to_form";
    }

    @RequestMapping("/rejestrowanie")
    public String registerCheck(Model model,
                                @RequestParam String name,
                                @RequestParam String email,
                                @RequestParam String password) {
        if (userRepo.findUserByEmail(email) == null) {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            userRepo.save(user);
            return "redirect:/logowanie";
        } else {
            model.addAttribute("error", "Podany adres email jest już zajęty");
            return "home/registration";
        }
    }

    @RequestMapping("/zalogowanie")
    public String logInCheck(Model model, RedirectAttributes redirect,
                             @RequestParam String email,
                             @RequestParam String password) {
        if (password.equals(userRepo.findPasswordByEmail(email))) {
            redirect.addAttribute("user", userRepo.findUserByEmail(email));
            return "redirect:/panel/zalogowanie";
        } else {
            model.addAttribute("errorMsg", "Podano złe dane logowania");
            return "home/login";
        }
    }

}
