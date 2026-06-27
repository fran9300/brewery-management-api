package com.brewery.api.service;

import com.brewery.api.dto.ingredient.IngredientResponse;
import com.brewery.api.dto.recipe.RecipeRequest;
import com.brewery.api.dto.recipe.RecipeResponse;
import com.brewery.api.exception.ResourceNotFoundException;
import com.brewery.api.model.Ingredient;
import com.brewery.api.model.Recipe;
import com.brewery.api.repository.IngredientRepository;
import com.brewery.api.repository.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;


    public RecipeService(
            RecipeRepository recipeRepository,
            IngredientRepository ingredientRepository
    ) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }


    public List<RecipeResponse> findAll(){

        return recipeRepository.findAllWithIngredients()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    public RecipeResponse save(RecipeRequest request){

        Recipe recipe = new Recipe();

        recipe.setName(request.name());
        recipe.setStyle(request.style());
        recipe.setInstructions(request.instructions());


        return mapToResponse(
                recipeRepository.save(recipe)
        );
    }

    @Transactional
    public RecipeResponse addIngredientToRecipe(
            Long recipeId,
            Long ingredientId
    ){

        Recipe recipe = findEntityById(recipeId);


        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ingredient not found")
                );


        recipe.getIngredients().add(ingredient);


        return mapToResponse(
                recipeRepository.save(recipe)
        );
    }


    public RecipeResponse findById(Long id){

        return mapToResponse(
                findEntityById(id)
        );
    }

    @Transactional
    public RecipeResponse update(
            Long id,
            RecipeRequest request
    ){

        Recipe recipe = findEntityById(id);


        recipe.setName(request.name());
        recipe.setStyle(request.style());
        recipe.setInstructions(request.instructions());


        return mapToResponse(
                recipeRepository.save(recipe)
        );
    }


    public void delete(Long id){

        Recipe recipe = findEntityById(id);

        recipeRepository.delete(recipe);
    }



    private RecipeResponse mapToResponse(Recipe recipe){

        List<IngredientResponse> ingredients =
                recipe.getIngredients()
                        .stream()
                        .map(this::mapIngredientToResponse)
                        .toList();


        return new RecipeResponse(
                recipe.getId(),
                recipe.getName(),
                recipe.getStyle(),
                recipe.getInstructions(),
                ingredients
        );
    }



    private IngredientResponse mapIngredientToResponse(
            Ingredient ingredient
    ){

        return new IngredientResponse(
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType(),
                ingredient.getQuantity(),
                ingredient.getUnit()
        );
    }



    private Recipe findEntityById(Long id){

        return recipeRepository.findByIdWithIngredients(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Recipe not found")
                );
    }
}
