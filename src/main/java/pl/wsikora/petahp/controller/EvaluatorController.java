package pl.wsikora.petahp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.wsikora.petahp.model.entities.Criterion;
import pl.wsikora.petahp.model.entities.Preference;
import pl.wsikora.petahp.model.entities.Survey;
import pl.wsikora.petahp.model.repositories.*;

import java.time.LocalDate;
import java.util.*;

@Controller
public class EvaluatorController {
    private AnimalRepo animalRepo;
    private CriterionRepo criterionRepo;
    private EvaluatorRepo evaluatorRepo;
    private FactRepo factRepo;
    private PreferenceRepo preferenceRepo;
    private SurveyRepo surveyRepo;
    private UserRepo userRepo;

    public EvaluatorController(AnimalRepo animalRepo,
                               CriterionRepo criterionRepo,
                               EvaluatorRepo evaluatorRepo,
                               FactRepo factRepo,
                               PreferenceRepo preferenceRepo,
                               SurveyRepo surveyRepo,
                               UserRepo userRepo) {
        this.animalRepo = animalRepo;
        this.criterionRepo = criterionRepo;
        this.evaluatorRepo = evaluatorRepo;
        this.factRepo = factRepo;
        this.preferenceRepo = preferenceRepo;
        this.surveyRepo = surveyRepo;
        this.userRepo = userRepo;
    }

    @RequestMapping(value = "/ankieta")
    public String form(@RequestParam String link,
                       Model model) {
        if (link.contains("http://localhost:8080/ankieta=")) {
            String path = link.replace("http://localhost:8080/ankieta=", "");
            return "redirect:/ankieta=" + path;
        } else {
            model.addAttribute("error", "Nie ma takiego linku :(");
            return "home/go_to_form";
        }
    }

    @RequestMapping(value = "/ankieta={link}")
    public String action(@PathVariable String link,
                         Model model) {
        Survey survey = surveyRepo.findByVotingLink(link);
        if (survey != null) {
            if (LocalDate.now().isBefore(survey.getEndDate()) && survey.getActualVotesNumber() <= survey.getEvaluatorNumber()) {
                model.addAttribute("survey", survey)
                        .addAttribute("animals", animalRepo.findAllBySurvey(survey));
                model.addAttribute("criteria", criterionRepo.findAllBySurvey(survey));


                //            model         .addAttribute("criteria", criterionRepo.findAllBySurvey(survey));
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



//    @RequestMapping(value = "/ankieta/zapisano-odpowiedz")
//    public String saveAnswer(@RequestParam Map<String, String> data) {
//        long pollId = Long.parseLong(data.get("pollId"));
//        long evaluatorId = evaluatorRepo.findEmptyEvaluators(pollId).get(0);
//        evaluatorRepo.updateName(data.get("evaluatorName"), evaluatorId);
//        Survey survey = pollRepo.findById(pollId);
//        int nb = survey.getActualNoOfVotes() + 1;
//        System.out.println(nb);
//        pollRepo.updateVisibility(nb, pollId);
//
//        for (Map.Entry<String, Integer> element : keyContains(data, "criterionRate").entrySet()) {
//
//            String ids = element.getKey().replace("criterionRate", "");
//            StringTokenizer st = new StringTokenizer(ids, "-");
//            long animalId = Long.parseLong(st.nextToken());
//            long criterionId = Long.parseLong(st.nextToken());
//
//            Preference cr = new Preference();
//            cr.setPoll(survey);
//            cr.setCriterion(criterionRepo.findById(criterionId));
//            cr.setAnimal(animalRepo.findById(animalId));
//            cr.setValue(element.getValue());
//            criterionResultRepo.save(cr);
//        }
//
//        for (Map.Entry<String, Integer> element : keyContains(data, "subCriterionRate").entrySet()) {
//
//            String ids = element.getKey().replace("subCriterionRate", "");
//            StringTokenizer st = new StringTokenizer(ids, "-");
//            long animalId = Long.parseLong(st.nextToken());
//            long subCriterionId = Long.parseLong(st.nextToken());
//
//            SubCriterionResult scr = new SubCriterionResult();
//            scr.setSurvey(survey);
//            scr.setSubCriterion(subCriterionRepo.findById(subCriterionId));
//            scr.setAnimal(animalRepo.findById(animalId));
//            scr.setValue(element.getValue());
//            subCriterionResultRepo.save(scr);
//        }
//        return "redirect:/ankieta/podsumowanie";
//    }
//
//    @RequestMapping(value = "/ankieta/podsumowanie")
//    public String summarize() {
//        return "evaluator/summary";
//    }

}
