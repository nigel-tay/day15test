package sg.nus.iss.vttp.day15test.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    PetsRedis petsRedis;

    @GetMapping
    public String returnIndexPage(Model model) {
        model.addAttribute("pet", new Pet());
        return "index";
    }

    @GetMapping(path="/petlist")
    public String returnPetListPage(Model model) {
        String redisKey = "PET_HASH";
        Map<Object, Object> petObj = petsRedis.retrieveAllValues(redisKey);
        List<Pet> petObjList = new ArrayList<>();
        
        for (Map.Entry<Object, Object> entry : petObj.entrySet()) {
            Pet pet = (Pet) entry.getValue();
            petObjList.add(pet);
        }

        model.addAttribute("petObj", petObjList);
        return "petList";

    }

    @PostMapping(consumes ="application/x-www-form-urlencoded", path="/form")
    public String handleForm(Pet pet, Model model) {
        System.out.println(pet.getName());
        petsRedis.addPet(pet);
        model.addAttribute("filledPet", pet);
        return "pet";
    }
}
