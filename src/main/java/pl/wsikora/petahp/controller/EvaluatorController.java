package pl.wsikora.petahp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.wsikora.petahp.model.entities.CriterionResult;
import pl.wsikora.petahp.model.entities.Evaluator;
import pl.wsikora.petahp.model.entities.Poll;
import pl.wsikora.petahp.model.entities.SubCriterionResult;
import pl.wsikora.petahp.model.repositories.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

@Controller
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

    @RequestMapping(value = "/ankieta")
    public String form(@RequestParam String link) {
        String path = link.replace("http://localhost:8080/ankieta=", "");
        return "redirect:/ankieta=" + path;
    }

    @RequestMapping(value = "/ankieta={link}")
    public String action(@PathVariable String link, Model model) {
        if (pollRepo.checkLink(link) == 1) {
            long id = pollRepo.getId(link);
            if (LocalDate.now().isBefore(pollRepo.getEndDate(id)) && evaluatorRepo.countEmptyEvaluators(id) > 0) {
                model.addAttribute("animals", animalRepo.findAllByPollId(id))
                        .addAttribute("criteria", criterionRepo.findAllByPollId(id))
                        .addAttribute("subCriteria", subCriterionRepo.findAllByPollId(id))
//                        .addAttribute("evaluator", evaluatorRepo.findAllByPollId(id))
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
    private Map<String, Integer> keyContains(Map<String, String> map, String name) {
        Map<String, Integer> map1 = new HashMap<>();
        for (Map.Entry<String, String> element : map.entrySet()) {
            if (!element.getValue().equals("")) {
                if (element.getKey().contains(name)) {
                    map1.put(element.getKey(), Integer.parseInt(element.getValue()));
                    map.remove(element);
                }
            }
        }
        return map1;
    }

    @RequestMapping(value = "/ankieta/zapisano-odpowiedz")
    public String saveAnswer(@RequestParam Map<String, String> data) {
        long pollId = Long.parseLong(data.get("pollId"));
        long evaluatorId = evaluatorRepo.findEmptyEvaluators(pollId).get(0);
        evaluatorRepo.updateName(data.get("evaluatorName"), evaluatorId);
        Poll poll = pollRepo.findById(pollId);

        for (Map.Entry<String, Integer> element : keyContains(data, "criterionRate").entrySet()) {

            String ids = element.getKey().replace("criterionRate", "");
            StringTokenizer st = new StringTokenizer(ids, "-");
            long animalId = Long.parseLong(st.nextToken());
            long criterionId = Long.parseLong(st.nextToken());

            CriterionResult cr = new CriterionResult();
            cr.setPoll(poll);
            cr.setCriterion(criterionRepo.findById(criterionId));
            cr.setAnimal(animalRepo.findById(animalId));
            cr.setValue(element.getValue());
            criterionResultRepo.save(cr);
        }

        for (Map.Entry<String, Integer> element : keyContains(data, "subCriterionRate").entrySet()) {

            String ids = element.getKey().replace("subCriterionRate", "");
            StringTokenizer st = new StringTokenizer(ids, "-");
            long animalId = Long.parseLong(st.nextToken());
            long subCriterionId = Long.parseLong(st.nextToken());

            SubCriterionResult scr = new SubCriterionResult();
            scr.setPoll(poll);
            scr.setSubCriterion(subCriterionRepo.findById(subCriterionId));
            scr.setAnimal(animalRepo.findById(animalId));
            scr.setValue(element.getValue());
            subCriterionResultRepo.save(scr);
        }
        return "redirect:/ankieta/podsumowanie";
    }

    @RequestMapping(value = "/ankieta/podsumowanie")
    public String summarize() {
        return "evaluator/summary";
    }

}
