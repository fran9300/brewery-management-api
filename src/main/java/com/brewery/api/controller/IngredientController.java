package com.brewery.api.controller;

import com.brewery.api.model.Ingredient;
import com.brewery.api.service.IngredientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ingredients")
@CrossOrigin(origins = "*") //permitir peticiones desde cualquier origen (para probar en mi front basico)
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public List<Ingredient> getAll() {
        return ingredientService.findAll();
    }

    @GetMapping("/{id}")
    public Ingredient getById(@PathVariable Long id) {
        return ingredientService.findById(id);
    }

    @PostMapping
    public Ingredient create(@Valid @RequestBody Ingredient ingredient) {
        return ingredientService.save(ingredient);
    }

    @PutMapping("/{id}")
    public Ingredient update(
            @PathVariable Long id,
            @Valid @RequestBody Ingredient ingredient) {

        return ingredientService.update(id, ingredient);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        ingredientService.delete(id);
    }
}
