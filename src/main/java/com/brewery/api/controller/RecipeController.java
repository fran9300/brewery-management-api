package com.brewery.api.controller;

import com.brewery.api.dto.recipe.RecipeRequest;
import com.brewery.api.dto.recipe.RecipeResponse;
import com.brewery.api.model.Recipe;
import com.brewery.api.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/recipes")
@CrossOrigin(origins = "*")
@Tag(
        name = "Recipes",
        description = "Operations related to brewery recipes"
)
public class RecipeController {


    private final RecipeService recipeService;


    public RecipeController(RecipeService recipeService){

        this.recipeService = recipeService;
    }


    @Operation(
            summary = "Get all recipes"
    )
    @GetMapping
    public List<RecipeResponse> getAll() {

        return recipeService.findAll();
    }


    @Operation(
            summary = "Get recipe by ID"
    )
    @GetMapping("/{id}")
    public RecipeResponse getById(@PathVariable Long id){

        return recipeService.findById(id);
    }


    @Operation(
            summary = "Create a recipe"
    )
    @PostMapping
    public RecipeResponse create(
            @Valid @RequestBody RecipeRequest request) {

        return recipeService.save(request);
    }


    @Operation(
            summary = "Add ingredient to recipe"
    )
    @PostMapping("/{recipeId}/ingredients/{ingredientId}")
    public RecipeResponse  addIngredient(
            @PathVariable Long recipeId,
            @PathVariable Long ingredientId){

        return recipeService.addIngredientToRecipe(recipeId, ingredientId);
    }


    @Operation(
            summary = "Update a recipe"
    )
    @PutMapping("/{id}")
    public RecipeResponse update(
            @PathVariable Long id,
            @Valid @RequestBody RecipeRequest request) {

        return recipeService.update(id, request);
    }


    @Operation(
            summary = "Delete a recipe"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){

        recipeService.delete(id);
    }

}