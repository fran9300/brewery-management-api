package com.brewery.api.dto.recipe;

import com.brewery.api.dto.ingredient.IngredientResponse;

import java.util.List;



public record RecipeResponse (

    Long id,

    String name,

    String style,

    String instructions,

    List<IngredientResponse> ingredients

) {}
