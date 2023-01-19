package me.golovina.recipebook1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

public class Recipe {
    private String nameRecipe;
    private int time;
    private List<Ingredient> ingredients;
    private List<String> preparingSteps;

    public StringBuilder stepsToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < preparingSteps.size(); i++)
            sb.append(preparingSteps.get(i) + '\n');
        return sb;
    }

    public StringBuilder ingredientsToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ingredients.size(); i++)
            sb.append(ingredients.get(i).toString() + '\n');
        return sb;
    }
}