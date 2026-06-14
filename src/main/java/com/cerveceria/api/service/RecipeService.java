package com.cerveceria.api.service;

import com.cerveceria.api.exception.ResourceNotFoundException;
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

    public List<Recipe> findAll(){
        return recipeRepository.findAll();
    }

    public Recipe create(Recipe recipe){
        return recipeRepository.save(recipe);
    }

    public Recipe addIngredientToRecipe(Long recipeId, Long ingredienteId){
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() ->
                                                                    new ResourceNotFoundException("Recipe not found"));
        Ingredient ingredient = ingredientRepository.findById(ingredienteId).orElseThrow(() ->
                                                                                    new ResourceNotFoundException("Ingredient not found"));
        recipe.getIngredients().add(ingredient);
        return recipeRepository.save(recipe);
    }

    public Recipe findById(Long id){
        return recipeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Recipe not found"));
    }

    public void delete(Long id){
        Recipe recipe = findById(id);
        recipeRepository.delete(recipe);
    }
}
