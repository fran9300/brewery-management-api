package com.brewery.api.service;

import com.brewery.api.dto.ingredient.IngredientRequest;
import com.brewery.api.dto.ingredient.IngredientResponse;
import com.brewery.api.exception.ResourceNotFoundException;
import com.brewery.api.model.Ingredient;
import com.brewery.api.repository.IngredientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class IngredientServiceTest {


    @Mock
    private IngredientRepository ingredientRepository;


    @InjectMocks
    private IngredientService ingredientService;



    @Test
    void shouldCreateIngredient(){

        IngredientRequest request =
                new IngredientRequest(
                        "lupulo",
                        "HOP",
                        10.0,
                        "GRAM"
                );


        Ingredient ingredient =
                new Ingredient();

        ingredient.setId(1L);
        ingredient.setName("LUPULO");
        ingredient.setType("HOP");
        ingredient.setQuantity(10.0);
        ingredient.setUnit("GRAM");


        when(ingredientRepository.save(any(Ingredient.class)))
                .thenReturn(ingredient);


        IngredientResponse response =
                ingredientService.save(request);


        assertEquals("LUPULO", response.name());
        assertEquals(1L, response.id());


        verify(ingredientRepository)
                .save(any(Ingredient.class));

    }



    @Test
    void shouldFindIngredientById(){

        Ingredient ingredient =
                new Ingredient();

        ingredient.setId(1L);
        ingredient.setName("MALTA");


        when(ingredientRepository.findById(1L))
                .thenReturn(Optional.of(ingredient));


        IngredientResponse response =
                ingredientService.findById(1L);


        assertEquals("MALTA", response.name());

    }




    @Test
    void shouldThrowExceptionWhenIngredientDoesNotExist(){

        when(ingredientRepository.findById(99L))
                .thenReturn(Optional.empty());


        assertThrows(
                ResourceNotFoundException.class,
                () -> ingredientService.findById(99L)
        );

    }



    @Test
    void shouldDeleteIngredient(){


        Ingredient ingredient =
                new Ingredient();

        ingredient.setId(1L);


        when(ingredientRepository.findById(1L))
                .thenReturn(Optional.of(ingredient));


        ingredientService.delete(1L);


        verify(ingredientRepository)
                .delete(ingredient);

    }

}
