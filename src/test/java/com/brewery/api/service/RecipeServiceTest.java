package com.brewery.api.service;


import com.brewery.api.dto.recipe.RecipeRequest;
import com.brewery.api.dto.recipe.RecipeResponse;
import com.brewery.api.exception.ResourceNotFoundException;
import com.brewery.api.model.Ingredient;
import com.brewery.api.model.Recipe;
import com.brewery.api.repository.IngredientRepository;
import com.brewery.api.repository.RecipeRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {


    @Mock
    private RecipeRepository recipeRepository;


    @Mock
    private IngredientRepository ingredientRepository;


    @InjectMocks
    private RecipeService recipeService;



    @Test
    void shouldCreateRecipe(){

        RecipeRequest request =
                new RecipeRequest(
                        "IPA",
                        "IPA STYLE",
                        "Add hops"
                );


        Recipe recipe = new Recipe();

        recipe.setId(1L);
        recipe.setName("IPA");
        recipe.setStyle("IPA STYLE");
        recipe.setInstructions("Add hops");


        when(recipeRepository.save(any(Recipe.class)))
                .thenReturn(recipe);


        RecipeResponse response =
                recipeService.save(request);


        assertEquals("IPA", response.name());
        assertEquals(1L, response.id());


        verify(recipeRepository)
                .save(any(Recipe.class));

    }




    @Test
    void shouldFindRecipeById(){

        Recipe recipe = new Recipe();

        recipe.setId(1L);
        recipe.setName("STOUT");

        recipe.setIngredients(
                new ArrayList<>()
        );


        when(recipeRepository.findById(1L))
                .thenReturn(Optional.of(recipe));


        RecipeResponse response =
                recipeService.findById(1L);


        assertEquals(
                "STOUT",
                response.name()
        );

    }





    @Test
    void shouldAddIngredientToRecipe(){

        Recipe recipe = new Recipe();

        recipe.setId(1L);
        recipe.setIngredients(
                new ArrayList<>()
        );



        Ingredient ingredient = new Ingredient();

        ingredient.setId(1L);
        ingredient.setName("LUPULO");



        when(recipeRepository.findById(1L))
                .thenReturn(Optional.of(recipe));


        when(ingredientRepository.findById(1L))
                .thenReturn(Optional.of(ingredient));


        when(recipeRepository.save(recipe))
                .thenReturn(recipe);


        RecipeResponse response =
                recipeService.addIngredientToRecipe(
                        1L,
                        1L
                );


        assertEquals(
                1,
                response.ingredients().size()
        );


        verify(recipeRepository)
                .save(recipe);

    }






    @Test
    void shouldThrowExceptionWhenRecipeDoesNotExist(){

        when(recipeRepository.findById(99L))
                .thenReturn(Optional.empty());


        assertThrows(
                ResourceNotFoundException.class,
                () -> recipeService.findById(99L)
        );

    }





    @Test
    void shouldDeleteRecipe(){

        Recipe recipe = new Recipe();

        recipe.setId(1L);


        when(recipeRepository.findById(1L))
                .thenReturn(Optional.of(recipe));


        recipeService.delete(1L);


        verify(recipeRepository)
                .delete(recipe);

    }


}
