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
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequestMapping("/panel")
public class UserController {
    private AnimalCriterionPreferenceRepo animalCriterionPreferenceRepo;
    private AnimalRepo animalRepo;
    private CriterionPreferenceRepo criterionPreferenceRepo;
    private CriterionRepo criterionRepo;
    private EvaluatorRepo evaluatorRepo;
    private EvaluatorResultRepo evaluatorResultRepo;
    private SurveyRepo surveyRepo;
    private UserRepo userRepo;

    public UserController(AnimalCriterionPreferenceRepo animalCriterionPreferenceRepo,
                          AnimalRepo animalRepo,
                          CriterionPreferenceRepo criterionPreferenceRepo,
                          CriterionRepo criterionRepo,
                          EvaluatorRepo evaluatorRepo,
                          EvaluatorResultRepo evaluatorResultRepo,
                          SurveyRepo surveyRepo,
                          UserRepo userRepo) {
        this.animalCriterionPreferenceRepo = animalCriterionPreferenceRepo;
        this.animalRepo = animalRepo;
        this.criterionPreferenceRepo = criterionPreferenceRepo;
        this.criterionRepo = criterionRepo;
        this.evaluatorRepo = evaluatorRepo;
        this.evaluatorResultRepo = evaluatorResultRepo;
        this.surveyRepo = surveyRepo;
        this.userRepo = userRepo;
    }

    private User currentUser;
    private Survey currentSurvey;

    public Map<String, String> getFilteredMapByMapKey(Map<String, String> map, String string) {
        return map.entrySet().stream()
                .filter(x -> x.getKey().contains(string))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @RequestMapping("/zalogowanie")
    public String logInToPanel(@RequestParam("user") User user) {
        if (user != null) {
            currentUser = user;
            return "redirect:/panel/";
        } else {
            return "redirect:/logowanie";
        }
    }

    @RequestMapping("")
    public String panelAction(Model model) {
        if (surveyRepo.countAllByUserAndStatus(currentUser, Status.FOUNDED) > 0) {
            model.addAttribute("surveys", surveyRepo.findAllByUserAndStatus(currentUser, Status.FOUNDED));
        }
        model.addAttribute("user", currentUser);
        return "user/panel";
    }

    @RequestMapping("/wyloguj")
    public String logOut() {
        currentUser = null;
        return "redirect:/";
    }

    @RequestMapping("/tworzenie-nowej-ankiety")
    public String createPoll(Model model) {
        model.addAttribute("minDate", LocalDate.now().plusDays(1));
        return "user/create_form";
    }

    @RequestMapping("/tworzenie-ankiety")
    public String savePoll(@RequestParam Map<String, String> data) {
        String[] parameters = {"surveyName", "evaluatorNumber", "endDate"};
        Survey survey = new Survey();
        survey.setUser(currentUser);
        survey.setName(data.get(parameters[0]));
        survey.setEvaluatorNumber(Integer.parseInt(data.get(parameters[1])));
        survey.setEndDate(LocalDate.parse(data.get(parameters[2])));
        surveyRepo.save(survey);
        currentSurvey = survey;

        for (String parameter : parameters) {
            data.remove(parameter);
        }

        for (int i = 0; i < survey.getEvaluatorNumber(); i++) {
            Evaluator evaluator = new Evaluator();
            evaluator.setSurvey(survey);
            evaluatorRepo.save(evaluator);
        }

        for (Map.Entry<String, String> animals : getFilteredMapByMapKey(data, "animal").entrySet()) {
            Animal animal = new Animal();
            animal.setSurvey(survey);
            animal.setName(animals.getValue().toLowerCase());
            animalRepo.save(animal);
            data.remove(animals.getKey());
        }

        List<String> keys = new ArrayList<>(data.keySet());
        List<Criterion> criteria = new ArrayList<>();
        for (int i = 0; i < data.size(); i += 3) {
            int level = Integer.parseInt(data.get(keys.get(i + 1)));
            Criterion criterion = new Criterion();
            criterion.setSurvey(survey);
            criterion.setName(data.get(keys.get(i)).toLowerCase());
            criterion.setHierarchyLevel(level);
            if (level > 1) {
                String parent = data.get(keys.get(i + 2)).substring(0, 19);
                int position = IntStream.range(0, keys.size())
                        .filter(k -> data.get(keys.get(k)).contains(parent))
                        .findFirst()
                        .getAsInt();
                criterion.setCriterion(criteria.get((position / 3) - 1));
            }
            criteria.add(criterion);
            criterionRepo.save(criterion);
        }
        return "redirect:/panel/ankieta-podsumowanie";
    }

    @RequestMapping("/ankieta-podsumowanie")
    public String summarizePoll(Model model) {
        model.addAttribute("survey", currentSurvey)
                .addAttribute("animals", animalRepo.findAllBySurvey(currentSurvey))
                .addAttribute("criteria", criterionRepo.findAllBySurvey(currentSurvey));
        return "user/summarize_form";
    }

    @RequestMapping("/pomoc")
    public String helpAction() {
        return "user/help";
    }

    @RequestMapping("/ustawienia")
    public String settingsAction() {
        return "user/settings";
    }

    @RequestMapping(value = "/szczegoly/{id}")
    public String editPoll(Model model, @PathVariable long id) {
        Survey survey = surveyRepo.findSurveyById(id);
        model.addAttribute("survey", survey)
                .addAttribute("animals", animalRepo.findAllBySurvey(survey))
                .addAttribute("criteria", criterionRepo.findAllBySurvey(survey));
        return "user/detail_form";
    }

    @RequestMapping("/usun/{id}")
    public String hidePoll(@PathVariable long id) {
        surveyRepo.updateStatus(Status.DELETED, id);
        return "redirect:/panel/";
    }

    @RequestMapping("/wynik/{id}")
    public String resultsAction(Model model, @PathVariable long id) {
        Survey survey = surveyRepo.findSurveyById(id);
        model.addAttribute("survey", survey)
                .addAttribute("evaluators", evaluatorRepo.findAllWithNotNullNameBySurvey(survey));
        return "user/results";
    }

}
