package com.ciena.recipemanager.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RecipeNotFoundException extends RuntimeException {
    public RecipeNotFoundException(String id) {
        super("Could not find recipe with id:  " + id);
        log.error("Could not find recipe with id: {}", id);
    }
}
