package pl.wsikora.petahp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.wsikora.petahp.model.entities.*;
import pl.wsikora.petahp.model.repositories.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/ankieta")
public class EvaluatorController {
    private PollRepo pollRepo;
    private EvaluatorRepo evaluatorRepo;

    public EvaluatorController(PollRepo pollRepo, EvaluatorRepo evaluatorRepo) {
        this.pollRepo = pollRepo;
        this.evaluatorRepo = evaluatorRepo;
    }

    @RequestMapping(value = "/{link}")
    public String action(@PathVariable String link) {
        System.out.println(pollRepo.checkLink(link));
        if (pollRepo.checkLink(link) == 1) {
            return "evaluator/login";
        } else {
            return "evaluator/badLink";
        }
    }

}
