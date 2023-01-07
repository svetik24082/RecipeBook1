package me.golovina.recipebook1.servic;

import me.golovina.recipebook1.model.Ingredient;

import java.util.Collection;

public interface IngredientService {
    Collection<Ingredient> getAllIngredient();
    Ingredient addIngredient (Ingredient ingredient);
    Ingredient getIngredientById (int idIngredient);
}

