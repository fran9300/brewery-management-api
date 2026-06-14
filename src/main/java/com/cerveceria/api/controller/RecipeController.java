package com.cerveceria.api.controller;

import com.cerveceria.api.model.Ingredient;
import com.cerveceria.api.model.Recipe;
import com.cerveceria.api.service.IngredientService;
import com.cerveceria.api.service.RecipeService;
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
        return recipeService.listarTodas();
    }

    @PostMapping
    public Recipe create(@RequestBody Recipe recipe) {
        return recipeService.crear(recipe);
    }

    @PostMapping("/{recipeId}/ingredients/{ingredientId}")
    public Recipe addIngredient(@PathVariable Long recipeId,
                                @PathVariable Long ingredientId){
        return recipeService.agregarIngredienteAReceta(recipeId, ingredientId);
    }

}
