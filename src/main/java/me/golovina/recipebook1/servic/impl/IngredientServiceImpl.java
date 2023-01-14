package me.golovina.recipebook1.servic.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.golovina.recipebook1.exception.ExceptionWithIngredientVerification;
import me.golovina.recipebook1.model.Ingredient;

import me.golovina.recipebook1.servic.IngredientFilesService;
import me.golovina.recipebook1.servic.IngredientService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientFilesService ingredientFilesService;
    private Map<Long, Ingredient> ingredients = new HashMap<>();
    private long id = 0;

    public IngredientServiceImpl(IngredientFilesService ingredientFilesService) {
        this.ingredientFilesService = ingredientFilesService;
    }

    @PostConstruct
    private void init() throws ExceptionWithIngredientVerification {  // в этом
        readIngredientFromFile();
    }

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
            saveIngredientToFile();
        }
        return ingredient;
    }

    @Override
    public Ingredient getIngredientById(long id) throws ExceptionWithIngredientVerification {
        if (ingredients.containsKey(id)) {
            return ingredients.get(id);
        } else {
            throw new ExceptionWithIngredientVerification(" Такого ингредиента нет!");
        }
    }

    @Override
    public Ingredient update(long id, Ingredient ingredient) throws ExceptionWithIngredientVerification {
        if (ingredients.containsKey(id)) {
            saveIngredientToFile();
            return ingredients.put(id, ingredient);

        } else {
            throw new ExceptionWithIngredientVerification(" Вы пытаетесь обновить не существующий ингредиент!");
        }

    }

    @Override
    public Ingredient remove(long id) {
        return ingredients.remove(id);
    }

    private void saveIngredientToFile() {  // сохр в файл
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            ingredientFilesService.saveIngredientToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readIngredientFromFile() throws ExceptionWithIngredientVerification { // чтение из файла
        String json = ingredientFilesService.readIngredientFromFile();
        try {
            ingredients = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new ExceptionWithIngredientVerification("Не удается прочитать ингредиент");
        }

    }
}
