package com.brewery.api.controller;

import com.brewery.api.model.Recipe;
import com.brewery.api.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/recipes")
@CrossOrigin(origins = "*")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<Recipe> getAll() {
        return recipeService.findAll();
    }

    @GetMapping("/{id}")
    public Recipe getById(@PathVariable Long id){
        return recipeService.findById(id);
    }

    @PostMapping
    public Recipe create(@Valid @RequestBody Recipe recipe) {
        return recipeService.create(recipe);
    }

    @PostMapping("/{recipeId}/ingredients/{ingredientId}")
    public Recipe addIngredient(@PathVariable Long recipeId,
                                @PathVariable Long ingredientId){
        return recipeService.addIngredientToRecipe(recipeId, ingredientId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        recipeService.delete(id);
    }

}
