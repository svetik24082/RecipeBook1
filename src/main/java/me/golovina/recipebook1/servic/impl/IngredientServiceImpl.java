package me.golovina.recipebook1.servic.impl;

import me.golovina.recipebook1.model.Ingredient;
import me.golovina.recipebook1.servic.IngredientService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final Map<Long, Ingredient> ingredients = new HashMap<>();
    private long id = 0;

    @Override
    public Collection<Ingredient> getAllIngredient() {// получение всех ингред
        return ingredients.values();
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {  // доб рец
        if (ingredients.containsKey(id)) {
            throw new RuntimeException("Такой ингредиент уже есть!");
        } else {
            ingredients.put(id++, ingredient);
        }
        return ingredient;
    }

    @Override
    public Ingredient getIngredientById(long id) {
        if (ingredients.containsKey(id)) {
            return ingredients.get(id);
        } else {
            throw new RuntimeException(" Такого ингредиента нет!");
        }
    }

    @Override
    public Ingredient update(long id, Ingredient ingredient) {
        if (ingredients.containsKey(id)) {
            return ingredients.put(id, ingredient);
        }
        return null;
    }

    @Override
    public Ingredient remove(long id) {
        return ingredients.remove(id);
    }
}
