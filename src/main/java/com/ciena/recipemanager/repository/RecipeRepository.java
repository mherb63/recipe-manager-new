package com.ciena.recipemanager.repository;

import com.ciena.recipemanager.domain.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RecipeRepository extends MongoRepository<Recipe, String> {
    Optional<Recipe> findByContributorName(String contributorName);
    Optional<Recipe> findByTitle(String title);
}