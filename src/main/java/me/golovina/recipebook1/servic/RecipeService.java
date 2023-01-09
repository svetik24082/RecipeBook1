package me.golovina.recipebook1.servic;

import me.golovina.recipebook1.model.Recipe;

import java.util.Collection;

public interface RecipeService {
    Collection<Recipe> getAll();

    Recipe addRecipe(Recipe recipe);

    Recipe getRecipeById(long id);

    Recipe update(long id, Recipe recipe);

    Recipe remove(long id);


}

