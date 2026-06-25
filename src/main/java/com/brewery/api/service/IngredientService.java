package com.brewery.api.service;

import com.brewery.api.dto.ingredient.IngredientRequest;
import com.brewery.api.dto.ingredient.IngredientResponse;
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

    public List<IngredientResponse> findAll() {

        return ingredientRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public IngredientResponse save(IngredientRequest request) {

        Ingredient ingredient = new Ingredient();

        ingredient.setName(request.name().toUpperCase());
        ingredient.setType(request.type());
        ingredient.setQuantity(request.quantity());
        ingredient.setUnit(request.unit());

        Ingredient saved = ingredientRepository.save(ingredient);


        return mapToResponse(saved);
    }

    public IngredientResponse findById(Long id) {

        Ingredient ingredient = findEntityById(id);

        return mapToResponse(ingredient);
    }

    public IngredientResponse update(Long id, IngredientRequest request) {

        Ingredient ingredient = findEntityById(id);

        ingredient.setName(request.name().toUpperCase());
        ingredient.setType(request.type());
        ingredient.setQuantity(request.quantity());
        ingredient.setUnit(request.unit());


        Ingredient updated = ingredientRepository.save(ingredient);


        return mapToResponse(updated);
    }

    public void delete(Long id) {

        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ingredient not found")
                );

        ingredientRepository.delete(ingredient);

    }

    private IngredientResponse mapToResponse(Ingredient ingredient) {


        return new IngredientResponse(
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType(),
                ingredient.getQuantity(),
                ingredient.getUnit()
        );

    }

    private Ingredient findEntityById(Long id) {

        return ingredientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ingredient not found")
                );
    }


}
