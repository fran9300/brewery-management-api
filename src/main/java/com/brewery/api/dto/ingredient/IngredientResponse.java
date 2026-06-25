package com.brewery.api.dto.ingredient;

public record IngredientResponse(

        Long id,
        String name,
        String type,
        Double quantity,
        String unit

) {}
