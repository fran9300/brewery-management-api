package com.brewery.api.controller;

import com.brewery.api.dto.IngredientRequest;
import com.brewery.api.dto.IngredientResponse;
import com.brewery.api.model.Ingredient;
import com.brewery.api.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/ingredients")
@Tag(
        name = "Ingredients",
        description = "Operations related to brewery ingredients"
)
public class IngredientController {


    private final IngredientService ingredientService;


    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }


    @Operation(
            summary = "Get all ingredients",
            description = "Returns all available ingredients"
    )
    @GetMapping
    public List<IngredientResponse> getAll() {

        return ingredientService.findAll();
    }


    @Operation(
            summary = "Get ingredient by ID"
    )
    @GetMapping("/{id}")
    public IngredientResponse getById(@PathVariable Long id) {
        return ingredientService.findById(id);
    }


    @Operation(
            summary = "Create a new ingredient"
    )
    @PostMapping
    public IngredientResponse create(
            @Valid @RequestBody IngredientRequest request) {

        return ingredientService.save(request);
    }


    @Operation(
            summary = "Update an ingredient"
    )
    @PutMapping("/{id}")
    public IngredientResponse update(
            @PathVariable Long id,
            @Valid @RequestBody IngredientRequest request) {

        return ingredientService.update(id, request);
    }


    @Operation(
            summary = "Delete an ingredient"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ingredientService.delete(id);
    }

}