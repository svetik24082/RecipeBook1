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

}