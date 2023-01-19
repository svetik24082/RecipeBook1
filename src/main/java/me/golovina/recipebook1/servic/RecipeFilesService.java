package me.golovina.recipebook1.servic;

import me.golovina.recipebook1.exception.ExceptionWithCheckingRecipes;

import java.io.File;
import java.nio.file.Path;

public interface RecipeFilesService {
    boolean saveRecipeToFile(String json);

    String readRecipeFromFile() throws ExceptionWithCheckingRecipes;

    boolean cleanDataFile();


    File getDataFile();

    Path createTempFile(String suffix);
}
