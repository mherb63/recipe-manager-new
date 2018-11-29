package com.ciena.recipemanager.controller;

import com.ciena.recipemanager.domain.Recipe;
import com.ciena.recipemanager.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")

public class RecipeController {

    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<Recipe> findAll() {
        return this.recipeService.findAll();
    }

    @GetMapping ("/{id}")
    public Recipe findById(@PathVariable String id) {
        return this.recipeService.findById(id);
    }

    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return this.recipeService.createRecipe(recipe);
    }

    @PutMapping("/{id}")
    public Recipe replaceRecipe(@PathVariable String id, @RequestBody Recipe recipe) {
        Recipe r = this.recipeService.findById(id);
        return this.recipeService.replaceRecipe(recipe);
    }

    @DeleteMapping("/{id}")
    public Recipe deleteRecipe(@PathVariable String id) {
        return this.recipeService.deleteRecipe(id);
    }
}
