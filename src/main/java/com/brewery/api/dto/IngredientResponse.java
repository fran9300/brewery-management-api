package com.brewery.api.dto;

public record IngredientResponse(

        Long id,
        String name,
        String type,
        Double quantity,
        String unit

) {}
