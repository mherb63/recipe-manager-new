package com.ciena.recipemanager.service;

import com.ciena.recipemanager.domain.Recipe;
import com.ciena.recipemanager.exception.RecipeNotFoundException;
import com.ciena.recipemanager.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RecipeService {

    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> findAll() {
        log.info("Received request to get all recipes");
        return recipeRepository.findAll();
    }

    public Recipe findById(String id) {
        log.info("Received request to find Recipe with id: {}", id);
        return recipeRepository.findById(id).orElseThrow(() -> new RecipeNotFoundException("Could not find Recipe with id: " + id));
    }

    public Recipe createRecipe(Recipe recipe) {
        log.info("Received request to create a new Recipe: {}", recipe);

        Recipe newRecipe = new Recipe();
        newRecipe.setTitle(recipe.getTitle());
        newRecipe.setContributorName(recipe.getContributorName());
        newRecipe.setInstructions(recipe.getInstructions());
        newRecipe.setNotes(recipe.getNotes());

        return recipeRepository.save(newRecipe);
    }

    public Recipe replaceRecipe(Recipe recipe) {
        Recipe r = findById(recipe.getId());

        log.info("Received request to replace a Recipe, old Recipe: {}  new Recipe: {}", r, recipe);
        return recipeRepository.save(recipe);
    }

    public Recipe deleteRecipe(String id) {
        log.info("Received request to delete Recipe with id: {}", id);

        Recipe r = this.findById(id);
        recipeRepository.deleteById(id);
        return r;
    }

    public Recipe findByTitle(String title) {
        return recipeRepository.findByTitle(title).orElseThrow(() -> new RecipeNotFoundException("Could not find Recipe with title: " + title));
    }

    public Recipe findByContributorName(String contributorName) {
        return recipeRepository.findByContributorName(contributorName).orElseThrow(() -> new RecipeNotFoundException("Could not find Recipe with contributor name: " + contributorName));
    }
}
