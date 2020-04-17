package pl.wsikora.petahp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.wsikora.petahp.model.entities.Animal;
import pl.wsikora.petahp.model.entities.Poll;
import pl.wsikora.petahp.model.entities.User;
import pl.wsikora.petahp.model.repositories.AnimalRepository;
import pl.wsikora.petahp.model.repositories.PollRepository;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/panel/ankieta-tworzenie")
public class FormCreatController {

    private PollRepository pollRepository;
    private AnimalRepository animalRepository;

    private User currentUser = new User();
    private Poll currentPoll;
    private Integer noOfCriteria;

    public FormCreatController(PollRepository pollRepository, AnimalRepository animalRepository) {
        this.pollRepository = pollRepository;
        this.animalRepository = animalRepository;
    }

    @RequestMapping("")
    public String formAction1(Model model) {
        model.addAttribute("poll", new Poll());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentDate", LocalDate.now());
        return "panel/form/init/step1";
    }

    @RequestMapping("/krok-1")
    public String initFormAction1(@ModelAttribute Poll poll) {
        pollRepository.save(poll);
        currentPoll = poll;
        return "redirect:/panel/ankieta-tworzenie/zwierzeta";
    }

    @RequestMapping("/zwierzeta")
    public String initAnimalsFormAction() {
        return "panel/form/init/step2";
    }

    @RequestMapping("/krok-2")
    public String initFormAction2(@RequestParam Integer numberOfAnimals, Model model) {
        model.addAttribute("noOfAnimals", numberOfAnimals);
        return "panel/form/init/step3";
    }

    @RequestMapping("/krok-3")
    public String initFormAction3(@RequestParam  Map<String, String> animals) {

        for (int i = 0; i < animals.size(); i++) {
          Animal animal = new Animal();
          animal.setPoll(currentPoll);
//          animal.setName(animals.);
        }
        return "/panel/form/init/step4";
    }

    @RequestMapping("/krok-4")
    public String initFormAction4(@RequestParam Integer numberOfCriteria, Model model) {
        model.addAttribute("numberOfCriteria", numberOfCriteria);
        return "panel/form/init/step5";
    }

    @RequestMapping("/krok-5")
    public String initFormAction5(@RequestParam Integer numberOfCriteria, Model model) {
        noOfCriteria = numberOfCriteria;
        model.addAttribute("numberOfCriteria", noOfCriteria);
        return "panel/form/init/step5";
    }

    @RequestMapping("/krok-6")
    public String initFormAction6(@RequestParam Integer noOfSubCriterion, Model model) {
        if (noOfSubCriterion > 0) {
            model.addAttribute("noOfSubCriterion", noOfSubCriterion);
            return "panel/form/init/step6";
        } else {
            noOfCriteria--;
            return "redirect:/panel/ankieta-tworzenie/krok-5";
        }
    }

    @RequestMapping("/krok-7")
    public String initFormAction7(@RequestParam Integer noOfSubCriterion, Model model) {
        noOfCriteria--;
        if (noOfCriteria > 0) {
            return "redirect:/panel/ankieta-tworzenie/krok-6";
        } else {
            return "redirect:/panel";
        }
    }


    @RequestMapping("/kryteria")
    public String initCriteriaFormAction() {
        return "panel/form/init/step4";
    }
}
