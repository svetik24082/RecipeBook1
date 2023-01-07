package me.golovina.recipebook1.controller;

import me.golovina.recipebook1.model.Recipe;
import me.golovina.recipebook1.servic.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
@RestController
@RequestMapping("/recipe")
public class RecipeController {
    public final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @GetMapping
    public Collection<Recipe>getAll() {
        return this.recipeService.getAll();
    }
        @GetMapping("/{id}")
                public Recipe getRecipeById(@PathVariable("id")long id){
            return recipeService.getRecipeById(id);
    }
    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return this.recipeService.addRecipe(recipe);
    }
    @PutMapping("/{id}")
    public  Recipe updateRecipe(@PathVariable("id")long id,@RequestBody Recipe recipe){
        return recipeService.update(id,recipe);
    }
    @DeleteMapping("/{id}")
    public Recipe deleteRecipe(@PathVariable("id")long id){
        return recipeService.remove(id);
    }
}
