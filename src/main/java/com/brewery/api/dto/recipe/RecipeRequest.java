package com.brewery.api.dto.recipe;

import jakarta.validation.constraints.NotBlank;


public record RecipeRequest (


    @NotBlank(message = "Recipe name is required")
    String name,


    @NotBlank(message = "Style is required")
    String style,


    @NotBlank(message = "Instructions are required")
    String instructions

) {}
