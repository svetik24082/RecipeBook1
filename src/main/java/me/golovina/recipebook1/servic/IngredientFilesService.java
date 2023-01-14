package me.golovina.recipebook1.servic;

import me.golovina.recipebook1.exception.ExceptionWithIngredientVerification;

public interface IngredientFilesService {
    boolean saveIngredientToFile(String json);
    String readIngredientFromFile() throws ExceptionWithIngredientVerification;
    boolean cleanDataFile();

}
