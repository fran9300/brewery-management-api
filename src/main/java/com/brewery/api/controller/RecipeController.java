package com.brewery.api.controller;

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
    public List<Recipe> getAll() {

        return recipeService.findAll();
    }


    @Operation(
            summary = "Get recipe by ID"
    )
    @GetMapping("/{id}")
    public Recipe getById(@PathVariable Long id){

        return recipeService.findById(id);
    }


    @Operation(
            summary = "Create a recipe"
    )
    @PostMapping
    public Recipe create(
            @Valid @RequestBody Recipe recipe) {

        return recipeService.save(recipe);
    }


    @Operation(
            summary = "Add ingredient to recipe"
    )
    @PostMapping("/{recipeId}/ingredients/{ingredientId}")
    public Recipe addIngredient(
            @PathVariable Long recipeId,
            @PathVariable Long ingredientId){

        return recipeService.addIngredientToRecipe(recipeId, ingredientId);
    }


    @Operation(
            summary = "Update a recipe"
    )
    @PutMapping("/{id}")
    public Recipe update(
            @PathVariable Long id,
            @Valid @RequestBody Recipe recipe) {

        return recipeService.update(id, recipe);
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