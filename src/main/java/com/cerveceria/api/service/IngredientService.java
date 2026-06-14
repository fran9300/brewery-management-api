package com.cerveceria.api.service;

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

    public List<Ingredient> listarTodos() {
        return ingredientRepository.findAll();
    }

    public Ingredient guardar(Ingredient ingredient) {
        ingredient.setName(ingredient.getName().toUpperCase());
        return ingredientRepository.save(ingredient);
    }

    public Ingredient buscarPorId(Long id){
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Insumo no encontrado"));
    }

    public void eliminar(Long id){
        Ingredient ingredient = buscarPorId(id);
        ingredientRepository.delete(ingredient);
    }


}
