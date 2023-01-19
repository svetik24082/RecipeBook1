package me.golovina.recipebook1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Ingredient {
    private String nameIngredient;
    private int amount;
    private String unit;


    @Override
    public String toString() {
        return nameIngredient + ": " + amount + " " + unit;
    }
}
