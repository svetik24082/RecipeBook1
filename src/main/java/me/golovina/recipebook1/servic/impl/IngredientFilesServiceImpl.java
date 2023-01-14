package me.golovina.recipebook1.servic.impl;

import me.golovina.recipebook1.exception.ExceptionWithIngredientVerification;
import me.golovina.recipebook1.servic.IngredientFilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
@Service
public class IngredientFilesServiceImpl implements IngredientFilesService {
@Value("${path.to.data.file}")
private  String dataFilePath;
    @Value("${nameI.of.data.file}")
    private String dataFileName;


    @Override
    public boolean saveIngredientToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePath, dataFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readIngredientFromFile()  {
            try {
                return Files.readString(Path.of(dataFilePath, dataFileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    @Override
    public boolean cleanDataFile() {
        Path path = Path.of(dataFilePath, dataFileName);
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
