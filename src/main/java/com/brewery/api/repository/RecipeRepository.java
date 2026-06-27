package com.brewery.api.repository;

import com.brewery.api.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("""
            SELECT r
            FROM Recipe r
            LEFT JOIN FETCH r.ingredients
            """)
    List<Recipe> findAllWithIngredients();


    @Query("""
            SELECT r
            FROM Recipe r
            LEFT JOIN FETCH r.ingredients
            WHERE r.id = :id
            """)
    Optional<Recipe> findByIdWithIngredients(Long id);
}
