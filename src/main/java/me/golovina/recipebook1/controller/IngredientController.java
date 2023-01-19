package me.golovina.recipebook1.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.golovina.recipebook1.exception.ExceptionWithIngredientVerification;
import me.golovina.recipebook1.model.Ingredient;
import me.golovina.recipebook1.servic.IngredientService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/ingredients")
@Tag(name = "Ингредиенты", description = "Операции для работы с ингредиентами и другие эндпоинты")
public class IngredientController {
    @Qualifier("ingredientService")
    private final IngredientService ingredientService;


    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public Collection<Ingredient> getAllIngredient() {
        return this.ingredientService.getAllIngredient();
    }

    @GetMapping("/{id}")
    @Operation(
            description = "Получение ингредиента по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент был найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }

            )
    })
    public Ingredient getIngredientById(@PathVariable("id") long id) throws ExceptionWithIngredientVerification {
        return ingredientService.getIngredientById(id);
    }

    @PostMapping
    public ResponseEntity<?> addIngredient(@RequestBody Ingredient ingredient) {
        if (StringUtils.isBlank(ingredient.getNameIngredient())) {
            return ResponseEntity.badRequest().body("Название ингредиента не может быть пустым");
        }
        return ResponseEntity.ok(ingredientService.addIngredient(ingredient));


    }

    @PutMapping("/{id}")
    public Ingredient updateIngredient(@PathVariable("id") long id, @RequestBody Ingredient ingredient) throws ExceptionWithIngredientVerification {
        return ingredientService.update(id, ingredient);
    }

    @DeleteMapping("/{id}")
    public Ingredient deleteIngredient(@PathVariable("id") long id) {
        return ingredientService.remove(id);
    }
}
