package com.brewery.api.controller;


import com.brewery.api.dto.ingredient.IngredientRequest;
import com.brewery.api.dto.ingredient.IngredientResponse;
import com.brewery.api.service.IngredientService;

import org.springframework.dao.DataIntegrityViolationException;
import tools.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(IngredientController.class)
class IngredientControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;


    @MockitoBean
    private IngredientService ingredientService;



    @Test
    void shouldGetAllIngredients() throws Exception {


        IngredientResponse response =
                new IngredientResponse(
                        1L,
                        "LUPULO",
                        "HOP",
                        10.0,
                        "GRAM"
                );


        when(ingredientService.findAll())
                .thenReturn(List.of(response));


        mockMvc.perform(get("/api/ingredients"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$[0].name")
                        .value("LUPULO"));

    }



    @Test
    void shouldCreateIngredient() throws Exception {


        IngredientRequest request =
                new IngredientRequest(
                        "lupulo",
                        "HOP",
                        10.0,
                        "GRAM"
                );


        IngredientResponse response =
                new IngredientResponse(
                        1L,
                        "LUPULO",
                        "HOP",
                        10.0,
                        "GRAM"
                );


        when(ingredientService.save(any()))
                .thenReturn(response);


        mockMvc.perform(
                        post("/api/ingredients")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                )


                .andExpect(status().isOk())

                .andExpect(jsonPath("$.name")
                        .value("LUPULO"));


        verify(ingredientService)
                .save(any());

    }



    @Test
    void shouldDeleteIngredient() throws Exception {


        doNothing()
                .when(ingredientService)
                .delete(1L);


        mockMvc.perform(
                        delete("/api/ingredients/1")
                )


                .andExpect(status().isNoContent());


        verify(ingredientService)
                .delete(1L);

    }



    @Test
    void shouldNotCreateDuplicateIngredient() throws Exception {

        IngredientRequest request =
                new IngredientRequest(
                        "LUPULO",
                        "HOP",
                        10.0,
                        "GRAM"
                );


        when(ingredientService.save(any()))
                .thenThrow(new DataIntegrityViolationException("uq_ingredients_name"));


        mockMvc.perform(
                        post("/api/ingredients")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )

                .andExpect(status().isConflict())

                .andExpect(jsonPath("$.message")
                        .value("Ingredient name already exists"));
    }



    @Test
    void shouldNotDeleteIngredientUsedInRecipe() throws Exception {


        doThrow(
                new DataIntegrityViolationException(
                        "fk_ingredient"
                )
        )
                .when(ingredientService)
                .delete(1L);



        mockMvc.perform(
                        delete("/api/ingredients/1")
                )


                .andExpect(status().isConflict())


                .andExpect(jsonPath("$.message")
                        .value(
                                "Ingredient cannot be deleted because it is used in a recipe"
                        ));

    }


}