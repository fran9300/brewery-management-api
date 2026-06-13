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

    public Ingredient actualizar(Long id, Ingredient datosActualizados){

        Ingredient ingrediente = buscarPorId(id);

        ingrediente.setName(
                datosActualizados.getName().toUpperCase()
        );

        ingrediente.setType(
                datosActualizados.getType()
        );

        ingrediente.setQuantity(
                datosActualizados.getQuantity()
        );

        ingrediente.setUnit(
                datosActualizados.getUnit()
        );

        return ingredientRepository.save(ingrediente);
    }

    public void eliminar(Long id){

        Ingredient ingrediente = buscarPorId(id);

        ingredientRepository.delete(ingrediente);
    }
}
