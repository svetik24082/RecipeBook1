package me.golovina.recipebook1.controller;

import me.golovina.recipebook1.servic.IngredientFilesService;
import me.golovina.recipebook1.servic.RecipeFilesService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files")
public class FilesController {
    private final RecipeFilesService recipeFilesService;
    private final IngredientFilesService ingredientFilesService;


    public FilesController(RecipeFilesService recipeFilesService, IngredientFilesService ingredientFilesService) {
        this.recipeFilesService = recipeFilesService;
        this.ingredientFilesService = ingredientFilesService;
    }

    @GetMapping(value = "/export", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputStreamResource> dowloadDataFile() throws FileNotFoundException {//скачать дата файл
        File file = recipeFilesService.getDataFile();
        if (file.exists()) {
            InputStreamResource resource =
                    new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipesLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();

        }
    }
}













