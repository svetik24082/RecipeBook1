package me.golovina.recipebook1.servic.impl;

import me.golovina.recipebook1.model.Recipe;
import me.golovina.recipebook1.servic.RecipeService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final Map<Long, Recipe> recipes = new HashMap<>();

    private Long counter = 0L;

    @Override
    public Collection<Recipe> getAll() {// получение всех рец
        return recipes.values();
    }

    @Override
    public Recipe getRecipeById(long id) {
        if (recipes.containsKey(id)) {
            return recipes.get(id);
        } else {
            throw new RuntimeException(" Такого рецепта нет!");
        }
    }

    @Override
    public Recipe update(long id, Recipe recipe) {
        if (recipes.containsKey(id)) {
            recipes.put(id, recipe);
            return recipe;
        }
        return null;
    }

    @Override
    public Recipe remove(long id) {
        return recipes.remove(id);
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {  // доб рец
        if (recipes.containsKey(counter)) {
            throw new RuntimeException("Такой рецепт уже есть!");
        } else {
            recipes.put(this.counter++, recipe);
        }
        return recipe;
    }
}
