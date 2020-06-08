package pl.wsikora.petahp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.wsikora.petahp.algorithms.Ahp;
import pl.wsikora.petahp.model.entities.*;
import pl.wsikora.petahp.model.repositories.*;

import java.time.LocalDate;
import java.util.*;
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

    public double transform(String value) {
        double result;
        switch (Integer.parseInt(value)) {
            case 1:
                result = 9.;
                break;
            case 2:
                result = 8.;
                break;
            case 3:
                result = 7.;
                break;
            case 4:
                result = 6.;
                break;
            case 5:
                result = 5.;
                break;
            case 6:
                result = 4.;
                break;
            case 7:
                result = 3.;
                break;
            case 8:
                result = 2.;
                break;
            case 10:
                result = 1 / 2.;
                break;
            case 11:
                result = 1 / 3.;
                break;
            case 12:
                result = 1 / 4.;
                break;
            case 13:
                result = 1 / 5.;
                break;
            case 14:
                result = 1 / 6.;
                break;
            case 15:
                result = 1 / 7.;
                break;
            case 16:
                result = 1 / 8.;
                break;
            case 17:
                result = 1 / 9.;
                break;
            default:
                result = 1.;
                break;
        }
        return result;
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
        switch (survey.getStatus()) {
            case FOUNDED:
                if (survey.getActualVotesNumber().equals(survey.getEvaluatorNumber())) {
                    List<Evaluator> finalEvaluators = evaluatorRepo.findAllWithNotNullNameBySurvey(survey);
                    model.addAttribute("survey", survey)
                            .addAttribute("evaluators", finalEvaluators);
                    return "user/get_results";
                } else {
                    return "user/before_get_results";
                }
            case COMPLETED:
                return "redirect:panel/ostateczny/wynik/" + id;
            default:
                return "redirect:/panel/";
        }
    }

    @RequestMapping("/zapisz/wynik")
    public String saveResultsAction(@RequestParam("surveyId") long surveyId,
                                    @RequestParam Map<String, String> data) {

        data.remove("surveyId");

        Survey survey = surveyRepo.findSurveyById(surveyId);
        List<Evaluator> evaluators = evaluatorRepo.findAllWithNotNullNameBySurvey(survey);
        List<Double> evaluatorResults = evaluatorResultRepo.findAllValuesBySurvey(survey);

        Map<String, Double> map = new TreeMap<>();
        for (Map.Entry<String, String> e : data.entrySet()) {
            map.put(e.getKey(), transform(e.getValue()));
        }

        List<Double> preferences = new ArrayList<>(map.values());
        Ahp ahp = new Ahp(preferences);
        ahp.initialize();
        double[] weights = ahp.calcWeights();

        Map<Evaluator, List<Double>> finalEvaluatorResults = new HashMap<>();
        for (int i = 0; i < weights.length; i++) {
            List<Double> doubles = new ArrayList<>();
            for (int j = 0; j < evaluatorResults.size() / evaluators.size(); j++) {
                doubles.add(evaluatorResults.get(j) * weights[i]);
            }
            finalEvaluatorResults.put(evaluators.get(i), doubles);
        }

        System.out.println(finalEvaluatorResults.toString());

        return "user/results";
    }

    @RequestMapping("/ostateczny/wynik/{id}")
    public String finalResultsAction(Model model, @PathVariable long id) {
        Survey survey = surveyRepo.findSurveyById(id);
        model.addAttribute("survey", survey);
        return "user/results";
    }

}
