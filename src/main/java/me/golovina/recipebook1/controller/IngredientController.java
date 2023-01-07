package me.golovina.recipebook1.controller;

import me.golovina.recipebook1.model.Ingredient;
import me.golovina.recipebook1.model.Recipe;
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
    @GetMapping("/{id}")
    public Ingredient getIngredientById(@PathVariable("id")long id) {
        return ingredientService.getIngredientById(id);
    }

    @PostMapping
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return this.ingredientService.addIngredient(ingredient);

    }
    @PutMapping("/{id}")
    public  Ingredient updateIngredient(@PathVariable("id")long id,@RequestBody Ingredient ingredient){
        return ingredientService.update(id,ingredient);
    }
    @DeleteMapping("/{id}")
    public Ingredient deleteIngredient(@PathVariable("id")long id){
        return ingredientService.remove(id);
    }
}
