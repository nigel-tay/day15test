package sg.nus.iss.vttp.day15test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.nus.iss.vttp.day15test.model.Pet;

@Controller
@RequestMapping
public class MainController {

    @GetMapping
    public String returnIndexPage(Model model) {
        model.addAttribute("pet", new Pet());
        return "index";
    }

    @PostMapping(path="/form")
    public String handleForm(Pet pet) {
        System.out.println(pet.getName());
        return "pet";
    }
}
