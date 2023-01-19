package me.golovina.recipebook1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.golovina.recipebook1.servic.IngredientFilesService;
import me.golovina.recipebook1.servic.RecipeFilesService;
import me.golovina.recipebook1.servic.RecipeService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/files")

@Tag(name = "Работа с файлами", description = "получить и загрузить рецепты и ингредиенты в виде файла")
public class FilesController {
    private final RecipeFilesService recipeFilesService;
    private final IngredientFilesService ingredientFilesService;
    private final RecipeService recipeService;

    public FilesController(RecipeFilesService recipeFilesService, IngredientFilesService ingredientFilesService,
                           RecipeService recipeService) {
        this.recipeFilesService = recipeFilesService;
        this.ingredientFilesService = ingredientFilesService;
        this.recipeService = recipeService;
    }

    @Operation(
            summary = "Получение файла с рецептами",
            description = "в формате json"
    )

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

    @Operation(
            summary = "Скачать все рецепты",
            description = "в файле формата .txt"
    )
    @GetMapping("/recipesInFile")
    public ResponseEntity<InputStreamResource> getRecipesInTextFile() {
        try {
            Path path = recipeService.createRecipesFile();
            if (Files.size(path) == 0) {
                return ResponseEntity.noContent().build();
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(Files.size(path))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipesFile.txt\"")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }

}














