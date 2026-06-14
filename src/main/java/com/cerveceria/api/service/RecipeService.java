package com.cerveceria.api.service;

import com.cerveceria.api.model.Ingredient;
import com.cerveceria.api.model.Recipe;
import com.cerveceria.api.repository.IngredientRepository;
import com.cerveceria.api.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository){
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<Recipe> listarTodas(){
        return recipeRepository.findAll();
    }

    public Recipe crear(Recipe recipe){
        return recipeRepository.save(recipe);
    }

    public Recipe agregarIngredienteAReceta(Long recipeId, Long ingredienteId){
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new RuntimeException("receta no encontrada"));
        Ingredient ingredient = ingredientRepository.findById(ingredienteId).orElseThrow(() -> new RuntimeException("ingrediente no encontrado"));
        recipe.getIngredients().add(ingredient);
        return recipeRepository.save(recipe);
    }

    public Recipe buscarPorId(Long id){
        return recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada"));
    }
}
