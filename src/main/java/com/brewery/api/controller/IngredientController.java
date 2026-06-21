package com.brewery.api.controller;

import com.brewery.api.model.Ingredient;
import com.brewery.api.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/ingredients")
@CrossOrigin(origins = "*")
@Tag(
        name = "Ingredients",
        description = "Operations related to brewery ingredients"
)
public class IngredientController {


    private final IngredientService ingredientService;


    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }


    @Operation(
            summary = "Get all ingredients",
            description = "Returns all available ingredients"
    )
    @GetMapping
    public List<Ingredient> getAll() {

        return ingredientService.findAll();
    }


    @Operation(
            summary = "Get ingredient by ID"
    )
    @GetMapping("/{id}")
    public Ingredient getById(@PathVariable Long id) {

        return ingredientService.findById(id);
    }


    @Operation(
            summary = "Create a new ingredient"
    )
    @PostMapping
    public Ingredient create(
            @Valid @RequestBody Ingredient ingredient) {

        return ingredientService.save(ingredient);
    }


    @Operation(
            summary = "Update an ingredient"
    )
    @PutMapping("/{id}")
    public Ingredient update(
            @PathVariable Long id,
            @Valid @RequestBody Ingredient ingredient) {

        return ingredientService.update(id, ingredient);
    }


    @Operation(
            summary = "Delete an ingredient"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        ingredientService.delete(id);
    }

}