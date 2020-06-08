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

import static java.util.Map.Entry.comparingByKey;

@Controller
@RequestMapping("/ankieta")
public class EvaluatorController {
    private AnimalCriterionPreferenceRepo animalCriterionPreferenceRepo;
    private AnimalRepo animalRepo;
    private CriterionPreferenceRepo criterionPreferenceRepo;
    private CriterionRepo criterionRepo;
    private EvaluatorRepo evaluatorRepo;
    private EvaluatorResultRepo evaluatorResultRepo;
    private SurveyRepo surveyRepo;
    private UserRepo userRepo;

    public EvaluatorController(AnimalCriterionPreferenceRepo animalCriterionPreferenceRepo,
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

    private Map<String, Double> getFilteredMapByMapKey(Map<String, Double> map, String string) {
        return map.entrySet().stream()
                .filter(x -> x.getKey().contains(string))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Double> getResults(List<Double> smallWeights, List<Double> bigWeights, int animalNb) {
        List<Double> results = new ArrayList<>();
        double result;
        for (int i = 0; i < animalNb; i++) {
            result = 0;
            for (int j = 0; j < smallWeights.size(); j++) {
                result += smallWeights.get(j) * bigWeights.get(j + i * smallWeights.size());
            }
            results.add(result);
        }
        return results;
    }

    @RequestMapping("")
    public String form(@RequestParam String link, Model model) {
        if (link.matches("http://localhost:8080/ankieta/=\\w{6}")) {
            return "redirect:/ankieta/=" + link.split("=")[1];
        } else if (link.matches("http://localhost:8080/ankieta/wynik=\\w{6}")) {
            return "redirect:/ankieta/wynik=" + link.split("=")[1];
        } else {
            model.addAttribute("error", "Nie ma takiego linku :(");
            return "home/go_to_form";
        }
    }

    @RequestMapping("/={link}")
    public String action(@PathVariable String link, Model model) {
        Survey survey = surveyRepo.findByVotingLink(link);
        if (survey != null) {
            if (LocalDate.now().isBefore(survey.getEndDate())
                    && survey.getActualVotesNumber() < survey.getEvaluatorNumber()) {

                List<List<Criterion>> criteria = new ArrayList<>();
                criteria.add(criterionRepo.findAllBySurveyAndHierarchyLevel(survey, 1));

                for (Long id : criterionRepo.getAllParentId(survey.getId())) {
                    criteria.add(criterionRepo.findAllBySurveyIdAndParentId(survey.getId(), id));
                }

                model.addAttribute("survey", survey)
                        .addAttribute("criteriaSize", criteria.size())
                        .addAttribute("criteria", criteria)
                        .addAttribute("animals", animalRepo.findAllBySurvey(survey))
                        .addAttribute("noChildren", criterionRepo.findAllWithNoChildren(survey.getId()));

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

    @RequestMapping("/zapisano-odpowiedz")
    public String saveAnswer(@RequestParam("evaluatorName") String evaluatorName,
                             @RequestParam("surveyId") long surveyId,
                             @RequestParam("criteriaSize") int criteriaSize,
                             @RequestParam Map<String, String> data) {

        Survey survey = surveyRepo.findSurveyById(surveyId);
        Evaluator evaluator = evaluatorRepo.findFirstBySurveyAndNameNull(surveyId);
        evaluatorRepo.updateName(evaluatorName, evaluator);
        data.remove("evaluatorName");
        data.remove("surveyId");
        data.remove("criteriaSize");

        Map<String, Double>
                criteriaPart = new TreeMap<>(),
                animalsCriteriaPart = new TreeMap<>();
        for (Map.Entry<String, String> e : data.entrySet()) {
            if (e.getKey().contains("criteriaComparison")) {
                criteriaPart.put(e.getKey(), transform(e.getValue()));
            } else {
                animalsCriteriaPart.put(e.getKey(), transform(e.getValue()));
            }
        }

        List<Map<String, Double>> criteriaList = new ArrayList<>();
        for (int i = 0; i < criteriaSize; i++) {
            criteriaList.add(getFilteredMapByMapKey(criteriaPart, "criteriaComparison_" + i));
        }

        for (Map<String, Double> m : criteriaList) {
            List<Long> criteriaId = new ArrayList<>();
            List<Double> preferences = new ArrayList<>();
            for (Map.Entry<String, Double> e : m.entrySet()) {
                String[] ids = e.getKey().split("_");
                for (int i = 2; i < ids.length; i++) {
                    Long longId = Long.parseLong(ids[i]);
                    if (!criteriaId.contains(longId)) {
                        criteriaId.add(longId);
                    }
                }
                preferences.add(e.getValue());
            }
            Ahp ahp = new Ahp(preferences);
            ahp.initialize();
            double[] weights = ahp.calcWeights();
            for (int i = 0; i < weights.length; i++) {
                Criterion criterion = criterionRepo.findCriterionById(criteriaId.get(i));
                CriterionPreference cp = new CriterionPreference();
                cp.setSurvey(survey);
                cp.setEvaluator(evaluator);
                cp.setCriterion(criterion);
                if (criterion.getCriterion() != null) {
                    double parentWeight = criterionPreferenceRepo.getParentWeight(
                            surveyId,
                            evaluator.getId(),
                            criterion.getCriterion().getId());
                    cp.setWeight(weights[i] * parentWeight);
                } else {
                    cp.setWeight(weights[i]);
                }
                criterionPreferenceRepo.save(cp);
            }
        }

        List<Double> smallWeight = criterionPreferenceRepo.getAllEssentialWeights(
                evaluator.getId(),
                criterionRepo.findAllWithNoChildrenId(surveyId));

        List<Map<String, Double>> animalsCriteriaList = new ArrayList<>();
        int animalsCriteriaSize = criterionRepo.countAllWithNoChildren(surveyId);
        for (int i = 0; i < animalsCriteriaSize; i++) {
            animalsCriteriaList.add(getFilteredMapByMapKey(animalsCriteriaPart, "animalCriteriaComparison_" + i));
        }

        for (Map<String, Double> m : animalsCriteriaList) {
            List<Long>
                    animalsId = new ArrayList<>(),
                    criteriaId = new ArrayList<>();
            List<Double> preferences = new ArrayList<>();
            for (Map.Entry<String, Double> e : m.entrySet()) {
                String[] ids = e.getKey().split("_");
                for (int i = 2; i < ids.length; i++) {
                    Long id = Long.parseLong(ids[i]);
                    if (i < ids.length - 1) {
                        if (!animalsId.contains(id)) {
                            animalsId.add(id);
                        }
                    } else {
                        if (!criteriaId.contains(id)) {
                            criteriaId.add(id);
                        }
                    }
                }
                preferences.add(e.getValue());
            }
            Ahp ahp = new Ahp(preferences);
            ahp.initialize();
            double[] weights = ahp.calcWeights();
            for (int i = 0; i < weights.length; i++) {
                Criterion criterion = criterionRepo.findCriterionById(criteriaId.get(0));
                Animal animal = animalRepo.findAnimalById(animalsId.get(i));

                AnimalCriterionPreference acp = new AnimalCriterionPreference();
                acp.setSurvey(survey);
                acp.setEvaluator(evaluator);
                acp.setAnimal(animal);
                acp.setCriterion(criterion);
                acp.setWeight(weights[i]);
                animalCriterionPreferenceRepo.save(acp);
            }
        }

        List<Double> bigWeights = animalCriterionPreferenceRepo.getAllWeightBySurveyAndEvaluator(
                surveyId, evaluator.getId());

        int animalNb = animalRepo.countAllBySurvey(survey);
        List<Double> results = getResults(smallWeight, bigWeights, animalNb);
        List<Animal> animals = animalRepo.findAllBySurvey(survey);

        for (int i = 0; i < results.size(); i++) {
            EvaluatorResult er = new EvaluatorResult();
            er.setSurvey(survey);
            er.setEvaluator(evaluator);
            er.setAnimal(animals.get(i));
            er.setValue(results.get(i));
            evaluatorResultRepo.save(er);
        }

        survey.setActualVotesNumber(survey.getActualVotesNumber() + 1);
        surveyRepo.updateActualVotesNumber(survey.getActualVotesNumber(), survey.getId());
        return "redirect:/ankieta/podsumowanie/" + surveyId + "/" + evaluator.getId();
    }

    @RequestMapping("/podsumowanie/{surveyId}/{evaluatorId}")
    public String summarize(Model model,
                            @PathVariable long surveyId,
                            @PathVariable long evaluatorId) {
        Evaluator evaluator = evaluatorRepo.findEvaluatorById(evaluatorId);
        model.addAttribute("survey", surveyRepo.findSurveyById(surveyId))
                .addAttribute("names", evaluatorResultRepo.findAllNamesByEvaluator(evaluator))
                .addAttribute("values", evaluatorResultRepo.findAllValuesByEvaluator(evaluator));
        return "evaluator/summarize_form";
    }

    @RequestMapping("/wynik={link}")
    public String resultAction(@PathVariable String link, Model model) {
        Survey survey = surveyRepo.findByResultLink(link);
        if (survey != null) {
            if (survey.getStatus().equals(Status.COMPLETED)) {
                return "evaluator/results";
            } else {
                model.addAttribute("error", "Przedmiotowa ankieta nie udostępnia wyników");
                return "home/go_to_form";
            }
        } else {
            model.addAttribute("error", "Nie ma ankiety o zadanym linku :(");
            return "home/go_to_form";
        }
    }

}
