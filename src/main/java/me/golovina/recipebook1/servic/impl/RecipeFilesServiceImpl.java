package me.golovina.recipebook1.servic.impl;


import me.golovina.recipebook1.exception.ExceptionWithCheckingRecipes;
import me.golovina.recipebook1.servic.RecipeFilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class RecipeFilesServiceImpl implements RecipeFilesService {
    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${nameR.of.data.file}")
    private String dataFileName;

    @Override
    public boolean saveRecipeToFile(String json) {  // сохр в файл
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePath, dataFileName), json);
            return true;
        } catch (IOException e) {

            return false;
        }

    }

    @Override
    public String readRecipeFromFile() throws ExceptionWithCheckingRecipes {   //чтение из файла
        try {
            return Files.readString(Path.of(dataFilePath, dataFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean cleanDataFile() { //очистить файл
        try {
            Path path = Path.of(dataFilePath, dataFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            return false;
        }

    }
    @Override
    public File getDataFile(){
        return new File(dataFilePath + "/" + dataFileName);

    }

}
