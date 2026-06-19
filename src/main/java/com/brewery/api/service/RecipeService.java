package com.brewery.api.service;

import com.brewery.api.exception.ResourceNotFoundException;
import com.brewery.api.model.Ingredient;
import com.brewery.api.model.Recipe;
import com.brewery.api.repository.IngredientRepository;
import com.brewery.api.repository.RecipeRepository;
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

    public Recipe save(Recipe recipe){
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

    public Recipe update(Long id, Recipe updated) {

        Recipe recipe = findById(id);

        recipe.setName(updated.getName());
        recipe.setStyle(updated.getStyle());
        recipe.setInstructions(updated.getInstructions());

        return save(recipe);
    }

    public void delete(Long id){
        Recipe recipe = findById(id);
        recipeRepository.delete(recipe);
    }
}
