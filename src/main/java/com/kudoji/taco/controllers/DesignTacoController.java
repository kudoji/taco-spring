package com.kudoji.taco.controllers;

import com.kudoji.taco.Ingredient;
import com.kudoji.taco.Order;
import com.kudoji.taco.Taco;
import com.kudoji.taco.data.IngredientRepository;
import com.kudoji.taco.data.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository){
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @GetMapping
    public String showDesignForm(Model model){
        Map<String, List<Ingredient>> ingredientsMap = new HashMap<>();

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type: types){
            ingredientsMap.put(type.toString().toLowerCase(), new ArrayList<>());
        }

        ingredientRepository.findAll().forEach(i -> {
            String type = i.getType().toString().toLowerCase();
            List<Ingredient> ingredients = ingredientsMap.get(type);
            ingredients.add(i);
            ingredientsMap.put(type, ingredients);
        });

        ingredientsMap.forEach((s, ingredients) -> model.addAttribute(s, ingredients));

//        model.addAttribute("taco", new Taco());

        return "designForm";
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }


    @PostMapping
    public String processDesign(@Valid Taco taco,
                                Errors errors,
                                @ModelAttribute Order order){
        log.info("Processing design: " + taco);

        if (errors.hasErrors()){
            return "designForm";
        }

        Taco tacoUpdated = this.tacoRepository.save(taco);
        order.addTaco(tacoUpdated);

        return "redirect:/orders/current";
    }
}
