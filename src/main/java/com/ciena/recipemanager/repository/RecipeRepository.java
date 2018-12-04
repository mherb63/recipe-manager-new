package com.ciena.recipemanager.repository;

import com.ciena.recipemanager.domain.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecipeRepository extends MongoRepository<Recipe, String> {
    Recipe findByTitle(String title);
}