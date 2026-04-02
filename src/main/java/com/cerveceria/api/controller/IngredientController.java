package com.cerveceria.api.controller;

import com.cerveceria.api.model.Ingredient;
import com.cerveceria.api.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    @PostMapping
    public Ingredient create(@RequestBody Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }
}
