package pl.wsikora.petahp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.wsikora.petahp.model.repositories.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/ankieta")
public class EvaluatorController {
    private AnimalRepo animalRepo;
    private CriterionRepo criterionRepo;
    private CriterionResultRepo criterionResultRepo;
    private EvaluatorRepo evaluatorRepo;
    private PollRepo pollRepo;
    private SubCriterionRepo subCriterionRepo;
    private SubCriterionResultRepo subCriterionResultRepo;
    private UserRepo userRepo;

    public EvaluatorController(AnimalRepo animalRepo, CriterionRepo criterionRepo, CriterionResultRepo criterionResultRepo, EvaluatorRepo evaluatorRepo, PollRepo pollRepo, SubCriterionRepo subCriterionRepo, SubCriterionResultRepo subCriterionResultRepo, UserRepo userRepo) {
        this.animalRepo = animalRepo;
        this.criterionRepo = criterionRepo;
        this.criterionResultRepo = criterionResultRepo;
        this.evaluatorRepo = evaluatorRepo;
        this.pollRepo = pollRepo;
        this.subCriterionRepo = subCriterionRepo;
        this.subCriterionResultRepo = subCriterionResultRepo;
        this.userRepo = userRepo;
    }

    @RequestMapping(value = "")
    public String form(@RequestParam String link) {
        String path = link.replace("http://localhost:8080/ankieta/", "");
        return "redirect:/ankieta/" + path;
    }

    @RequestMapping(value = "/{link}")
    public String action(@PathVariable String link, Model model) {
        if (pollRepo.checkLink(link) == 1) {
            long id = pollRepo.getId(link);
            if (LocalDate.now().isBefore(pollRepo.getEndDate(id))) {
                model.addAttribute("animals", animalRepo.findAllByPollId(id))
                        .addAttribute("criteria", criterionRepo.findAllByPollId(id))
                        .addAttribute("subCriteria", subCriterionRepo.findAllByPollId(id))
                        .addAttribute("evaluator", evaluatorRepo.findAllByPollId(id))
                        .addAttribute("poll", pollRepo.findById(id));
                return "evaluator/complete_form";
            } else {
                model.addAttribute("error", "Przedmiotowa ankieta została zakończona");
                return "home/go_to_form";
            }
        } else {
            model.addAttribute("error", "Nie ma ankiety o zadanym linku :(");
            return "home/go_to_form";
        }
    }

}
