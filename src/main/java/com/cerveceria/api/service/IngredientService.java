package com.cerveceria.api.service;

import com.cerveceria.api.exception.ResourceNotFoundException;
import com.cerveceria.api.model.Ingredient;
import com.cerveceria.api.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient save(Ingredient ingredient) {
        ingredient.setName(ingredient.getName().toUpperCase());
        return ingredientRepository.save(ingredient);
    }

    public Ingredient findById(Long id){
        return ingredientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ingredient not found"));
    }

    public void eliminar(Long id){
        Ingredient ingredient = findById(id);
        ingredientRepository.delete(ingredient);
    }


}
