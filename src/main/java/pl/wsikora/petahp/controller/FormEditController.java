package pl.wsikora.petahp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wsikora.petahp.model.repositories.PollRepository;

@Controller
@RequestMapping("/panel/edycja-ankiet")
public class FormEditController {

    private PollRepository pollRepository;

    public FormEditController(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    @RequestMapping("")
    public String editFormAction(Model model) {
        model.addAttribute("forms", pollRepository.findAll());
        return "/panel/form/edit/edit_form";
    }
}
