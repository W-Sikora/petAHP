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

@Controller
public class UserController {
    private AnimalRepo animalRepo;
    private CriterionRepo criterionRepo;
    private CriterionResultRepo criterionResultRepo;
    private EvaluatorRepo evaluatorRepo;
    private PollRepo pollRepo;
    private SubCriterionRepo subCriterionRepo;
    private SubCriterionResultRepo subCriterionResultRepo;
    private UserRepo userRepo;

    public UserController(AnimalRepo animalRepo, CriterionRepo criterionRepo, CriterionResultRepo criterionResultRepo, EvaluatorRepo evaluatorRepo, PollRepo pollRepo, SubCriterionRepo subCriterionRepo, SubCriterionResultRepo subCriterionResultRepo, UserRepo userRepo) {
        this.animalRepo = animalRepo;
        this.criterionRepo = criterionRepo;
        this.criterionResultRepo = criterionResultRepo;
        this.evaluatorRepo = evaluatorRepo;
        this.pollRepo = pollRepo;
        this.subCriterionRepo = subCriterionRepo;
        this.subCriterionResultRepo = subCriterionResultRepo;
        this.userRepo = userRepo;
    }

    private User currentUser;
    private long currentPollId;

    private List<String> keyContains(Map<String, String> map, String string) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String> element : map.entrySet()) {
            if (!element.getValue().equals("")) {
                if (element.getKey().contains(string)) {
                    list.add(element.getValue());
                    map.remove(element);
                }
            }
        }
        return list;
    }
    private Integer getInt(Map<String, String> map, String key) throws NumberFormatException {
        return Integer.parseInt(map.get(key));
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
        long userId = currentUser.getId();
        int noOfVisible = pollRepo.countVisible(true, userId);
        if (noOfVisible > 0) {
            model.addAttribute("polls", pollRepo.getAllVisible(true, userId));
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
        List<String> criteriaNames = keyContains(data, "criterion");
        List<String> subCriteriaNames = keyContains(data, "subCriterion");

        Poll poll = new Poll();
        poll.setUser(currentUser);
        poll.setName(data.get("pollName"));
        poll.setNoOfVoters(getInt(data,"noOfVoters"));
        poll.setEndDate(LocalDate.parse(data.get("endDate")));
        pollRepo.save(poll);
        currentPollId = poll.getId();

        for (int i = 0; i < poll.getNoOfVoters(); i++) {
            Evaluator evaluator = new Evaluator();
            evaluator.setPoll(poll);
            evaluatorRepo.save(evaluator);
        }

        for (String animalName : keyContains(data, "animal")) {
            Animal animal = new Animal();
            animal.setPoll(poll);
            animal.setName(animalName);
            animalRepo.save(animal);
        }

        for (int i = 0; i < criteriaNames.size(); i++) {
            Criterion criterion = new Criterion();
            criterion.setPoll(poll);
            criterion.setName(criteriaNames.get(i));
            criterionRepo.save(criterion);

            int until = getInt(data, "noOfSubCriteria" + i);
            if (until > 0) {
                for (int j = 0; j < until; j++) {
                    SubCriterion subCriterion = new SubCriterion();
                    subCriterion.setPoll(poll);
                    subCriterion.setCriterion(criterion);
                    subCriterion.setName(subCriteriaNames.get(0));
                    subCriterionRepo.save(subCriterion);
                    subCriteriaNames.remove(0);
                }
            }
        }
        return "redirect:/panel/ankieta-podsumowanie";
    }

    @RequestMapping(value = "/panel/ankieta-podsumowanie")
    public String summarizePoll(Model model) {
        model.addAttribute("poll", pollRepo.findById(currentPollId))
                .addAttribute("animals", animalRepo.findAllByPollId(currentPollId))
                .addAttribute("criteria", criterionRepo.findAllByPollId(currentPollId))
                .addAttribute("subCriteria", subCriterionRepo.findAllByPollId(currentPollId));
        return "user/summarize_form";
    }

    @RequestMapping(value = "/panel/edycja-ankiet")
    public String editPoll(Model model) {
        if (pollRepo.countVisible(true, currentUser.getId()) > 0) {
            model.addAttribute("polls", pollRepo.getAllVisible(true, currentUser.getId()));
        }
        return "user/edit_form";
    }

    @RequestMapping(value = "/panel/edycja-ankiet/usun/{pollId}")
    public String hidePoll(@PathVariable long pollId) {
        pollRepo.updateVisibility(false, pollId);
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
    public String resultsAction(@PathVariable long id) {
        return "user/results";
    }


}
