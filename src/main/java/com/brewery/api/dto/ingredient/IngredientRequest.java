package com.brewery.api.dto.ingredient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record IngredientRequest(

        @NotBlank(message = "Ingredient name is required")
        String name,

        @NotBlank(message = "Ingredient type is required")
        String type,

        @Positive(message = "Quantity must be greater than zero")
        Double quantity,

        @NotBlank(message = "Unit is required")
        String unit

) {}
