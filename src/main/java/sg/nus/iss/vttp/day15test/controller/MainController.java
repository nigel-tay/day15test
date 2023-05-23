package sg.nus.iss.vttp.day15test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.nus.iss.vttp.day15test.model.Pet;
import sg.nus.iss.vttp.day15test.repository.PetsRedis;

@Controller
@RequestMapping
public class MainController {

    @Autowired
    PetsRedis petsRedisRepository;

    @GetMapping
    public String returnIndexPage(Model model) {
        model.addAttribute("pet", new Pet());
        return "index";
    }

    @PostMapping(consumes ="application/x-www-form-urlencoded", path="/form")
    public String handleForm(Pet pet, Model model) {
        System.out.println(pet.getName());
        petsRedisRepository.addPet(pet);
        model.addAttribute("filledPet", pet);
        return "pet";
    }
}
