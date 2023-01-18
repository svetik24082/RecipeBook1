package me.golovina.recipebook1.servic.impl;


import me.golovina.recipebook1.exception.ExceptionWithCheckingRecipes;
import me.golovina.recipebook1.exception.ServiceException;
import me.golovina.recipebook1.servic.RecipeFilesService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.springframework.http.HttpStatus.*;

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
            throw new ExceptionWithCheckingRecipes("Не удается прочитать файл");
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
    public File getDataFile() {
        return new File(dataFilePath + "/" + dataFileName);

    }


    @PostMapping(value = "/recipes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadRecipesDataFile(@RequestParam MultipartFile file) throws ServiceException {  //загрузка файла
        cleanDataFile();
        File dataFile = getDataFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();

        } catch (IOException e) {
            e.printStackTrace();

        }
        throw new  ServiceException  ("Ошибка внутреннего сервера");
    }
}
