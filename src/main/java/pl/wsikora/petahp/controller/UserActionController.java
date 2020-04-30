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
public class UserActionController {
    private UserRepo userRepo;
    private PollRepo pollRepo;
    private EvaluatorRepo evaluatorRepo;
    private AnimalRepo animalRepo;
    private CriterionRepo criterionRepo;
    private SubCriterionRepo subCriterionRepo;

    public UserActionController(UserRepo userRepo, PollRepo pollRepo, EvaluatorRepo evaluatorRepo, AnimalRepo animalRepo, CriterionRepo criterionRepo, SubCriterionRepo subCriterionRepo) {
        this.userRepo = userRepo;
        this.pollRepo = pollRepo;
        this.evaluatorRepo = evaluatorRepo;
        this.animalRepo = animalRepo;
        this.criterionRepo = criterionRepo;
        this.subCriterionRepo = subCriterionRepo;
    }

    private User currentUser;

    private final String MAIN_PAGE = "";
    private final String REGISTER = "/rejestracja";
    private final String REGISTER_CHECK = "/zarejestrowano";
    private final String LOG_IN = "/logowanie";
    private final String LOG_IN_CHECK = "/zalogowano";
    private final String LOGOUT = "/wyloguj";
    private final String PANEL = "/panel";
    private final String NEW_POLL = PANEL + "/tworzenie-nowej-ankiety";
    private final String SUMMARY = NEW_POLL + "/podsumowanie";
    private final String EDIT_POLL = PANEL + "/edycja-ankiet";

    @RequestMapping(value = MAIN_PAGE)
    public String homeAction() {
        return "index";
    }

    @RequestMapping(value = REGISTER)
    public String register() {
        return "login&registration/registration";
    }

    @RequestMapping(value = REGISTER_CHECK)
    public String registerCheck(@RequestParam Map<String, String> registrationData, Model model) {
        if (userRepo.findUserByEmail(registrationData.get("email")) == null) {
            User user = new User();
            user.setName(registrationData.get("name"));
            user.setEmail(registrationData.get("email"));
            user.setPassword(registrationData.get("password"));
            userRepo.save(user);
            return "redirect:" + LOG_IN;
        } else {
            model.addAttribute("registerError", "Podany adres email jest już zajęty");
            return "login&registration/registration";
        }
    }

    @RequestMapping(value = LOG_IN)
    public String login() {
        return "login&registration/login";
    }

    @RequestMapping(value = LOG_IN_CHECK)
    public String loginCheck(@RequestParam Map<String, String> loginData, Model model) {
        if (loginData.get("password").equals(userRepo.findPasswordByEmail(loginData.get("email")))) {
            currentUser = userRepo.findUserByEmail(loginData.get("email"));
            return "redirect:" + PANEL;
        } else {
            model.addAttribute("loginError", "Podano złe dane logowania");
            return "login&registration/login";
        }
    }

    @RequestMapping(value = LOGOUT)
    public String logoutAction() {
        currentUser = null;
        return "index";
    }

    @RequestMapping(value = PANEL)
    public String panelAction(Model model) {
        model.addAttribute("user", currentUser.getName());
        return "panel/panel";
    }

    @RequestMapping(value = NEW_POLL)
    public String newPollAction0(Model model) {
        model.addAttribute("minDate", LocalDate.now().plusDays(1));
        return "panel/form/init/form";
    }

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

    @RequestMapping(value = SUMMARY)
    public String newPollAction1(@RequestParam Map<String, String> data, Model model) {
        List<String> animalsNames = keyContains(data, "animal");
        List<String> criteriaNames = keyContains(data, "criterion");
        List<String> subCriteriaNames = keyContains(data, "subCriterion");

        Poll poll = new Poll();
        poll.setUser(currentUser);
        poll.setName(data.get("pollName"));
        poll.setNoOfVoters(Integer.parseInt(data.get("noOfVoters")));
        poll.setEndDate(LocalDate.parse(data.get("endDate")));
        pollRepo.save(poll);

        for (int i = 0; i < poll.getNoOfVoters(); i++) {
            Evaluator evaluator = new Evaluator();
            evaluator.setPoll(poll);
            evaluatorRepo.save(evaluator);
        }

        for (String animalName : animalsNames) {
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
            for (int j = 0; j < Integer.parseInt(data.get("noOfSubCriteria" + i)); j++) {
                SubCriterion subCriterion = new SubCriterion();
                subCriterion.setPoll(poll);
                subCriterion.setCriterion(criterion);
                subCriterion.setName(subCriteriaNames.get(j));
                subCriterionRepo.save(subCriterion);
            }
        }
        long id = poll.getId();
        model.addAttribute("poll", poll)
                .addAttribute("animals", animalRepo.findAllByPollId(id))
                .addAttribute("criteria", criterionRepo.findAllByPollId(id))
                .addAttribute("subCriteria", subCriterionRepo.findAllByPollId(id));
         return "panel/form/init/summary";
    }

    @RequestMapping(value = EDIT_POLL)
    public String editPollAction(Model model) {
        long id = currentUser.getId();
        if (pollRepo.countByVisibilityAndUserId(true, id) > 0) {
            model.addAttribute("polls", pollRepo.findAllByVisibilityAndUsersId(true, id));
        }
        return "panel/form/edit/edit_form";
    }

    @RequestMapping(value = EDIT_POLL + "/usun/{pollId}")
    public String deletePollAction(@PathVariable long pollId) {
        long id = currentUser.getId();
        pollRepo.updateVisibility(false, pollId);
        return "redirect:" + EDIT_POLL;
    }

}
