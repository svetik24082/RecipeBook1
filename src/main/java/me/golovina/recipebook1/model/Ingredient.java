package me.golovina.recipebook1.model;

public class Ingredient {
    private  String nameIngredient;
    private int amount;
    private  String unit;

    public Ingredient(String nameIngredient, int amount, String unit) {
        this.nameIngredient = nameIngredient;
        this.amount = amount;
        this.unit = unit;
    }

    public String getNameIngredient() {
        return nameIngredient;
    }

    public int getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setNameIngredient(String nameIngredient) {
        this.nameIngredient = nameIngredient;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
