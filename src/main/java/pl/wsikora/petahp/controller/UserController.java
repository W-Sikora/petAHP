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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
public class UserController {
    private AnimalRepo animalRepo;
    private CriterionRepo criterionRepo;
    private EvaluatorRepo evaluatorRepo;
    private FactRepo factRepo;
    private PreferenceRepo preferenceRepo;
    private SurveyRepo surveyRepo;
    private UserRepo userRepo;

    public UserController(AnimalRepo animalRepo,
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

    private User currentUser;
    private Survey currentSurvey;

    public List<String> getFilteredListByMapKey(Map<String, String> map, String string) {
        return map.entrySet().stream()
                .filter(x -> x.getKey().contains(string))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public Map<String, String> getFilteredMapByMapKey(Map<String, String> map, String string) {
        return map.entrySet().stream()
                .filter(x -> x.getKey().contains(string))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @RequestMapping(value = "/zalogowanie")
    public String checkLogin(@RequestParam String email,
                             @RequestParam String password,
                             Model model) {
        if (password.equals(userRepo.findPasswordByEmail(email))) {
            currentUser = userRepo.findUserByEmail(email);
            return "redirect:/panel";
        } else {
            model.addAttribute("errorMsg", "Podano złe dane logowania");
            return "home/login";
        }
    }

    @RequestMapping(value = "/wyloguj")
    public String logOut() {
        currentUser = null;
        return "redirect:/";
    }

    @RequestMapping(value = "/panel")
    public String panelAction(Model model) {
        if (surveyRepo.countAllByUserAndStatus(currentUser, Status.FOUNDED) > 0) {
            model.addAttribute("surveys", surveyRepo.findAllByUserAndStatus(currentUser, Status.FOUNDED));
        }
        model.addAttribute("user", currentUser.getName());
        return "user/panel";
    }

    @RequestMapping(value = "/panel/tworzenie-nowej-ankiety")
    public String createPoll(Model model) {
        model.addAttribute("minDate", LocalDate.now().plusDays(1));
        return "user/create_form";
    }

    @RequestMapping(value = "/panel/tworzenie-ankiety")
    public String savePoll(@RequestParam Map<String, String> data) {
        String surveyName = "surveyName";
        String evaluatorNumber = "evaluatorNumber";
        String endDate = "endDate";

        Survey survey = new Survey();
        survey.setUser(currentUser);
        survey.setName(data.get(surveyName));
        data.remove(surveyName);
        survey.setEvaluatorNumber(Integer.parseInt(data.get(evaluatorNumber)));
        data.remove(evaluatorNumber);
        survey.setEndDate(LocalDate.parse(data.get(endDate)));
        data.remove(endDate);
        surveyRepo.save(survey);
        currentSurvey = survey;

        for (int i = 0; i < survey.getEvaluatorNumber(); i++) {
            Evaluator evaluator = new Evaluator();
            evaluator.setSurvey(survey);
            evaluatorRepo.save(evaluator);
        }

        for (Map.Entry<String, String> animals : getFilteredMapByMapKey(data, "animal").entrySet()) {
            Animal animal = new Animal();
            animal.setSurvey(survey);
            animal.setName(animals.getValue());
            animalRepo.save(animal);
            data.remove(animals.getKey());
        }

        List<String> keys = new ArrayList<>(data.keySet());
//        for (int i = 0; i < data.size(); i += 3) {
//            Criterion criterion = new Criterion();
//            criterion.setSurvey(survey);
//            criterion.setName(data.get(keys.get(i)));
//            criterion.setHierarchyLevel(Integer.parseInt(data.get(keys.get(i + 1))));
//            criterion.setCriterion(data.get(keys.get(i + 2));
//        }
        System.out.println(data.toString());
        return "redirect:/panel/ankieta-podsumowanie";
    }

    @RequestMapping(value = "/panel/ankieta-podsumowanie")
    public String summarizePoll(Model model) {
        model.addAttribute("survey", currentSurvey)
                .addAttribute("animals", animalRepo.findAllBySurvey(currentSurvey))
                .addAttribute("criteria", criterionRepo.findAllBySurvey(currentSurvey));
        return "user/summarize_form";
    }

//    @RequestMapping(value = "/panel/edycja-ankiet")
//    public String editPoll(Model model) {
//        if (pollRepo.countVisible(true, currentUser.getId()) > 0) {
//            model.addAttribute("polls", pollRepo.getAllVisible(true, currentUser.getId()));
//        }
//        return "user/edit_form";
//    }

    @RequestMapping(value = "/panel/edycja-ankiet/usun/{id}")
    public String hidePoll(@PathVariable long id) {
        surveyRepo.updateStatus(Status.DELETED, id);
        return "redirect:/panel";
    }

    @RequestMapping(value = "/panel/pomoc")
    public String helpAction() {
        return "user/help";
    }

    @RequestMapping(value = "/panel/ustawienia")
    public String settingsAction() {
        return "user/settings";
    }

    @RequestMapping(value = "/panel/wynik/{id}")
    public String resultsAction(@PathVariable long id,
                                Model model) {
        Survey survey = surveyRepo.findById(id);
        model.addAttribute("survey", survey)
                .addAttribute("animals", animalRepo.findAllBySurvey(survey));
        return "user/results";
    }


}
