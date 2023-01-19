package me.golovina.recipebook1.servic.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.golovina.recipebook1.exception.ExceptionWithCheckingRecipes;
import me.golovina.recipebook1.model.Recipe;

import me.golovina.recipebook1.servic.RecipeService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeFilesServiceImpl recipeFilesService;
    private Map<Long, Recipe> recipes = new HashMap<>();

    private Long counter = 0L;

    public RecipeServiceImpl(RecipeFilesServiceImpl recipeFilesService) {
        this.recipeFilesService = recipeFilesService;
    }


    @PostConstruct
    private void init() throws ExceptionWithCheckingRecipes {  // в этом
        readRecipeFromFile();
    }

    @Override
    public Collection<Recipe> getAll() {// получение всех рец
        return recipes.values();
    }

    @Override
    public Recipe getRecipeById(long id) throws ExceptionWithCheckingRecipes {
        if (recipes.containsKey(id)) {
            return recipes.get(id);
        } else {
            throw new ExceptionWithCheckingRecipes(" Такого рецепта нет!");
        }
    }

    @Override
    public Recipe update(long id, Recipe recipe) throws ExceptionWithCheckingRecipes {
        if (recipes.containsKey(id)) {
            recipes.put(id, recipe);
            saveRecipeToFile();
            return recipe;
        } else {
            throw new ExceptionWithCheckingRecipes(" Вы пытаетесь обновить не существующий рецепт!");
        }

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
            saveRecipeToFile();
        }
        return recipe;
    }

    private void saveRecipeToFile() {  // сохр в файл
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            recipeFilesService.saveRecipeToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readRecipeFromFile() throws ExceptionWithCheckingRecipes { // чтение из файла
        String json = recipeFilesService.readRecipeFromFile();
        try {
            recipes = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new ExceptionWithCheckingRecipes("Не удается прочитать рецепт");
        }
    }

    @Override
    public Path createRecipesFile() throws IOException {
        Path path = recipeFilesService.createTempFile("recipes");
        for (Recipe recipe : recipes.values()) {
            try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append("Название рецепта: " + recipe.getNameRecipe() + '\n' + '\n' +
                        "Время приготовления: " + recipe.getTime() + '\n' + '\n' +
                        "Ингредиенты: " + '\n' + '\n' + recipe.ingredientsToString() + '\n' +
                        "Инструкция приготовления: " + '\n' + '\n' + recipe.stepsToString() + '\n');
            }
        }
        return path;
    }
}

