package com.brewery.api.service;

import com.brewery.api.exception.ResourceNotFoundException;
import com.brewery.api.model.Ingredient;
import com.brewery.api.repository.IngredientRepository;
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

    public Ingredient update(Long id, Ingredient updated) {

        Ingredient ingredient = findById(id);

        ingredient.setName(updated.getName());
        ingredient.setType(updated.getType());
        ingredient.setQuantity(updated.getQuantity());
        ingredient.setUnit(updated.getUnit());

        return ingredientRepository.save(ingredient);
    }

    public void delete(Long id){
        Ingredient ingredient = findById(id);
        ingredientRepository.delete(ingredient);
    }


}
