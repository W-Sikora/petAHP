package pl.wsikora.petahp.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wsikora.petahp.model.entities.User;
import pl.wsikora.petahp.model.repositories.UserRepo;

@Controller
@RequestMapping("/rejestracja")
public class RegistrationController {
    private final UserRepo repository;
    private final PasswordEncoder encoder;

    public RegistrationController(UserRepo repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @GetMapping
    public String register() {
        return "home/registration";
    }

    @PostMapping
    public String registerCheck(String name, String email, String password, Model model) {
        if (repository.existsByEmail(email)) {
            model.addAttribute("error", String.format("Podany adres email (%s) jest już zajęty", email));
            return "home/registration";
        } else {
            repository.save(new User(name, email, encoder.encode(password)));
            return "redirect:/logowanie";
        }
    }

}
