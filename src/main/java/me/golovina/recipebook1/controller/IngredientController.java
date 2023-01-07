package me.golovina.recipebook1.controller;

import me.golovina.recipebook1.model.Ingredient;
import me.golovina.recipebook1.servic.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;


    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public Collection<Ingredient> getAllIngredient() {
        return this.ingredientService.getAllIngredient();
    }

    @PostMapping
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return this.ingredientService.addIngredient(ingredient);

    }
}
