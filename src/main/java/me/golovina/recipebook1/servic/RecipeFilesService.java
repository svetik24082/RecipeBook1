package me.golovina.recipebook1.servic;

import me.golovina.recipebook1.exception.ExceptionWithCheckingRecipes;

public interface RecipeFilesService {
    boolean saveRecipeToFile(String json);
    String readRecipeFromFile() throws ExceptionWithCheckingRecipes;
    boolean cleanDataFile();


}
