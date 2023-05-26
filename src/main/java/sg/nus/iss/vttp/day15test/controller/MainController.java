package sg.nus.iss.vttp.day15test.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.nus.iss.vttp.day15test.model.Pet;
import sg.nus.iss.vttp.day15test.repository.PetsRedis;

@Controller
@RequestMapping
public class MainController {

    @Autowired
    PetsRedis petsRedis;

    @GetMapping
    public String returnIndexPage(Model model) {
        model.addAttribute("pet", new Pet());
        return "index";
    }

    @GetMapping(path="/petlist")
    public String returnPetListPage(@RequestParam(required = false) String id, Model model) {
        if (!(id == null)) {
            String name = ((Pet)petsRedis.getPet(id)).getName();
            int age = ((Pet)petsRedis.getPet(id)).getAge();
            String url = ((Pet)petsRedis.getPet(id)).getImageUrl();
            Pet filledPet = new Pet(name, id, age, url);
            model.addAttribute("filledPet", filledPet);
            return "pet";
        }
        else {
            String redisKey = "PET_HASH";
            Map<Object, Object> petObj = petsRedis.retrieveAllValues(redisKey);
    
            model.addAttribute("petObj", petObj);
            return "petList";
        }
    }

    @PostMapping(consumes ="application/x-www-form-urlencoded", path="/form")
    public String handleForm(Pet pet, Model model) {
        pet.setId(pet.getName() + (new Date()).hashCode());
        petsRedis.addPet(pet);
        model.addAttribute("filledPet", pet);
        return "pet";
    }
}
