package pl.wsikora.petahp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.wsikora.petahp.model.entities.*;
import pl.wsikora.petahp.model.repositories.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserActionController {
    private UserRepo userRepo;
    private PollRepo pollRepo;
    private AnimalRepo animalRepo;
    private CriterionRepo criterionRepo;
    private SubCriterionRepo subCriterionRepo;

    public UserActionController(UserRepo userRepo, PollRepo pollRepo, AnimalRepo animalRepo, CriterionRepo criterionRepo, SubCriterionRepo subCriterionRepo) {
        this.userRepo = userRepo;
        this.pollRepo = pollRepo;
        this.animalRepo = animalRepo;
        this.criterionRepo = criterionRepo;
        this.subCriterionRepo = subCriterionRepo;
    }

    private User currentUser;
    private Poll currentPoll;
    private int currentNoOfCriteria;

    private final String MAIN_PAGE = "";
    private final String REGISTER = "/rejestracja";
    private final String REGISTER_CHECK = "/zarejestrowano";
    private final String LOG_IN = "/logowanie";
    private final String LOG_IN_CHECK = "/zalogowano";
    private final String LOGOUT = "/wyloguj";
    private final String PANEL = "/panel";
    private final String NEW_POLL = PANEL + "/tworzenie-nowej-ankiety/krok-";
    private final String SUMMARY = PANEL + "/tworzenie-nowej-ankiety/podsumowanie";
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

    @RequestMapping(value = NEW_POLL + "0")
    public String newPollAction0(Model model) {
        model.addAttribute("minDate", LocalDate.now().plusDays(1));
        return "panel/form/init/step1";
    }

    @RequestMapping(value = NEW_POLL + "1")
    public String newPollAction1(@RequestParam(name = "name") String name,
                                 @RequestParam(name = "noOfVoters") int noOfVoters,
                                 @RequestParam(name = "endDate") String endDate) {
        Poll poll = new Poll();
        poll.setUser(currentUser);
        poll.setName(name);
        poll.setNoOfVoters(noOfVoters);
        poll.setEndDate(LocalDate.parse(endDate));
        pollRepo.save(poll);
        currentPoll = poll;
        return "redirect:" + NEW_POLL + "2";
    }

    @RequestMapping(value = NEW_POLL + "2")
    public String newPollAction2() {
        return "panel/form/init/step2";
    }

    @RequestMapping(value = NEW_POLL + "3")
    public String newPollAction3(@RequestParam Map<String, String> animalData) {
        animalData.remove("noOfAnimals");
        for (int i = 0; i < animalData.size(); i++) {
            Animal animal = new Animal();
            animal.setPoll(currentPoll);
            animal.setName(animalData.get("animal" + i));
            animalRepo.save(animal);
        }
        return "redirect:" + NEW_POLL + "4";
    }

    @RequestMapping(value = NEW_POLL + "4")
    public String newPollAction4(Model model) {
        model.addAttribute("noOfCriteria", currentNoOfCriteria);
        return "panel/form/init/step3";
    }

    @RequestMapping(value = NEW_POLL + "5")
    public String newPollAction5(@RequestParam Map<String, String> criteriaData) {
        Map<String, String> criteria = new HashMap<>();
        Map<String, String> noOfSubCriteria = new HashMap<>();
        Map<String, String> subCriteria = new HashMap<>();
        for (Map.Entry<String, String> element : criteriaData.entrySet()) {
            String key = element.getKey();
            if (key.contains("noOfSubCriteria")) {
                noOfSubCriteria.put(element.getKey(), element.getValue());
            } else if (key.contains("subCriterionName")) {
                subCriteria.put(element.getKey(), element.getValue());
            } else {
                criteria.put(element.getKey(), element.getValue());
            }
        }
        for (int i = 0; i < criteria.size(); i++) {
            Criterion criterion = new Criterion();
            criterion.setName(criteria.get("name" + i));
            criterion.setPoll(currentPoll);
            criterionRepo.save(criterion);
            for (int j = 0; j < Integer.parseInt(noOfSubCriteria.get("noOfSubCriteria" + i)); j++) {
                SubCriterion subCriterion = new SubCriterion();
                subCriterion.setPoll(currentPoll);
                subCriterion.setCriterion(criterion);
                subCriterion.setName(subCriteria.get(i + "subCriterionName" + j));
                subCriterionRepo.save(subCriterion);
            }
        }
        return "redirect:" + SUMMARY;
    }

    @RequestMapping(value = SUMMARY)
    public String summaryPollAction(Model model) {
        long id = currentPoll.getId();
        model.addAttribute("poll", currentPoll)
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
