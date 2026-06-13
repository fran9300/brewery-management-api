package com.cerveceria.api.controller;

import com.cerveceria.api.model.Ingredient;
import com.cerveceria.api.service.IngredientService;
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
        return ingredientService.listarTodos();
    }

    @GetMapping("/{id}")
    public Ingredient getById(@PathVariable Long id){
        return ingredientService.buscarPorId(id);
    }

    @PostMapping
    public Ingredient create(@RequestBody Ingredient ingredient) {
        return ingredientService.guardar(ingredient);
    }

    @PutMapping("/{id}")
    public Ingredient update(
            @PathVariable Long id,
            @RequestBody Ingredient ingredient){

        return ingredientService.actualizar(
                id,
                ingredient
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){

        ingredientService.eliminar(id);
    }
}
