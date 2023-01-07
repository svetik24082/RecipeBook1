package me.golovina.recipebook1.model;

import java.util.List;

public class Recipe {
    private String nameRecipe;
    private int time;
    private List<Ingredient> ingredients;
    private List<String> preparingSteps;


    public Recipe( String nameRecipe, int time) {
        this.nameRecipe = nameRecipe;
        this.time = time;

    }

    public String getNameRecipe() {
        return nameRecipe;
    }

    public int getTime() {
        return time;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<String> getPreparingSteps() {
        return preparingSteps;
    }


    public void setNameRecipe(String nameRecipe) {
        this.nameRecipe = nameRecipe;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setPreparingSteps(List<String> preparingSteps) {
        this.preparingSteps = preparingSteps;
    }
}
