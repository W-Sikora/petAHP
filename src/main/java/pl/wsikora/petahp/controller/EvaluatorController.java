package pl.wsikora.petahp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.wsikora.petahp.model.repositories.*;

@Controller
@RequestMapping("/ankieta")
public class EvaluatorController {
    private PollRepo pollRepo;
    private EvaluatorRepo evaluatorRepo;

    public EvaluatorController(PollRepo pollRepo, EvaluatorRepo evaluatorRepo) {
        this.pollRepo = pollRepo;
        this.evaluatorRepo = evaluatorRepo;
    }

    @RequestMapping(value = "")
    public String form(@RequestParam String link) {
        return "redirect:/ankieta/" + link;
    }

    @RequestMapping(value = "/{link}")
    public String action(@PathVariable String link, Model model) {
        System.out.println(pollRepo.checkLink(link));
        if (pollRepo.checkLink(link) == 1) {
            return "evaluator/complete_form";
        } else {
            model.addAttribute("error", "Nie ma ankiety o zadanym linku :(");
            return "home/go_to_form";
        }
    }

}
