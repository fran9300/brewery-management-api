package com.brewery.api.controller;


import com.brewery.api.dto.recipe.RecipeRequest;
import com.brewery.api.dto.recipe.RecipeResponse;
import com.brewery.api.service.RecipeService;

import org.springframework.dao.DataIntegrityViolationException;
import tools.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(RecipeController.class)
class RecipeControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;


    @MockitoBean
    private RecipeService recipeService;



    @Test
    void shouldGetAllRecipes() throws Exception {


        RecipeResponse response =
                new RecipeResponse(
                        1L,
                        "American IPA",
                        "IPA",
                        "Boil malt for 60 minutes",
                        List.of()
                );


        when(recipeService.findAll())
                .thenReturn(List.of(response));


        mockMvc.perform(get("/api/recipes"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$[0].name")
                        .value("American IPA"));

    }



    @Test
    void shouldCreateRecipe() throws Exception {


        RecipeRequest request =
                new RecipeRequest(
                        "American IPA",
                        "IPA",
                        "Boil malt for 60 minutes"
                );


        RecipeResponse response =
                new RecipeResponse(
                        1L,
                        "American IPA",
                        "IPA",
                        "Boil malt for 60 minutes",
                        List.of()
                );


        when(recipeService.save(any()))
                .thenReturn(response);



        mockMvc.perform(
                        post("/api/recipes")
                                .contentType(APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                )


                .andExpect(status().isOk())

                .andExpect(jsonPath("$.name")
                        .value("American IPA"));



        verify(recipeService)
                .save(any());

    }



    @Test
    void shouldAddIngredientToRecipe() throws Exception {


        RecipeResponse response =
                new RecipeResponse(
                        1L,
                        "American IPA",
                        "IPA",
                        "Boil malt",
                        List.of()
                );


        when(recipeService.addIngredientToRecipe(1L, 2L))
                .thenReturn(response);



        mockMvc.perform(
                        post("/api/recipes/1/ingredients/2")
                )


                .andExpect(status().isOk())

                .andExpect(jsonPath("$.name")
                        .value("American IPA"));



        verify(recipeService)
                .addIngredientToRecipe(1L,2L);

    }



    @Test
    void shouldDeleteRecipe() throws Exception {


        doNothing()
                .when(recipeService)
                .delete(1L);



        mockMvc.perform(
                        delete("/api/recipes/1")
                )


                .andExpect(status().isNoContent());



        verify(recipeService)
                .delete(1L);

    }



    @Test
    void shouldUpdateRecipe() throws Exception {

        RecipeRequest request =
                new RecipeRequest(
                        "Updated IPA",
                        "IPA",
                        "New instructions"
                );


        RecipeResponse response =
                new RecipeResponse(
                        1L,
                        "Updated IPA",
                        "IPA",
                        "New instructions",
                        List.of()
                );


        when(recipeService.update(eq(1L), any()))
                .thenReturn(response);



        mockMvc.perform(
                        put("/api/recipes/1")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )


                .andExpect(status().isOk())

                .andExpect(jsonPath("$.name")
                        .value("Updated IPA"));


        verify(recipeService)
                .update(eq(1L), any());

    }



    @Test
    void shouldNotCreateDuplicateRecipe() throws Exception {


        RecipeRequest request =
                new RecipeRequest(
                        "American IPA",
                        "IPA",
                        "Boil malt"
                );


        when(recipeService.save(any()))
                .thenThrow(
                        new DataIntegrityViolationException(
                                "uq_recipes_name"
                        )
                );


        mockMvc.perform(
                        post("/api/recipes")
                                .contentType(APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                )

                .andExpect(status().isConflict())

                .andExpect(jsonPath("$.message")
                        .value("Recipe name already exists"));
    }



    @Test
    void shouldGetRecipeById() throws Exception {


        RecipeResponse response =
                new RecipeResponse(
                        1L,
                        "American IPA",
                        "IPA",
                        "Boil malt",
                        List.of()
                );


        when(recipeService.findById(1L))
                .thenReturn(response);



        mockMvc.perform(
                        get("/api/recipes/1")
                )


                .andExpect(status().isOk())

                .andExpect(jsonPath("$.name")
                        .value("American IPA"));

    }

}
