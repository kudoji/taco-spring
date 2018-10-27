package com.kudoji.taco.controllers;

import com.kudoji.taco.Ingredient;
import com.kudoji.taco.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model){
        Map<String, List<Ingredient>> ingredients = new HashMap<>();
        ingredients.put("Designate your wrap:", Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP)
        ));
        ingredients.put("Pick your protein:", Arrays.asList(
                new Ingredient("GRBF", "Groun Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN)
        ));
        ingredients.put("Determine your veggies:", Arrays.asList(
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES)
        ));
        ingredients.put("Choose your cheese:", Arrays.asList(
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE)
        ));
        ingredients.put("Select your sauce:", Arrays.asList(
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        ));

        model.addAttribute("ingredients", ingredients);

        model.addAttribute("taco", new Taco());

        return "designForm";
    }

    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors){
        log.info("Processing design: " + taco);

        if (errors.hasErrors()){
            return "designForm";
        }

        return "redirect:/orders/current";
    }
}
