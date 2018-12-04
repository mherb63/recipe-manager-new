package com.ciena.recipemanager.service;

import com.ciena.recipemanager.domain.Recipe;
import com.ciena.recipemanager.exception.RecipeNotFoundException;
import com.ciena.recipemanager.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceTest {

    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeService(recipeRepository);
    }

    @Test
    public void findRecipeByIdTest() {
        Recipe recipe = new Recipe();
        recipe.setId("recipeOne");
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById("recipeOne");

        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyString());
        verify(recipeRepository, never()).findAll();
    }

    @Test(expected = RecipeNotFoundException.class)
    public void findRecipeByIdTestRecipeNotFound() {

        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById("1");

        //should go boom
    }

    @Test
    public void findAllRecipesTest() {
        Recipe recipe = new Recipe();
        recipe.setId("recipeOne");
        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe);

        when(recipeService.findAll()).thenReturn(recipeList);

        List<Recipe> recipes = recipeService.findAll();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() {
        Recipe recipe = new Recipe();
        recipe.setId("recipeOne");
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        recipeService.deleteRecipe("recipeTwo");

        verify(recipeRepository, times(1)).deleteById(anyString());
    }

    @Test
    public void testFindByTitle() {
        String recipeTitle = "testRecipeTitle";
        Recipe recipe = new Recipe();
        recipe.setTitle(recipeTitle);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findByTitle(anyString())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findByTitle(recipeTitle);

        assertNotNull("Null recipe returned", recipeReturned);
        assertEquals(recipeTitle, recipeReturned.getTitle());
    }

    @Test void testFindByContributorName() {
        String contributorName = "testRecipeContributorName";
        Recipe recipe = new Recipe();
        recipe.setContributorName(contributorName);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findByContributorName(anyString())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findByTitle(contributorName);

        assertNotNull("Null recipe returned", recipeReturned);
        assertEquals(contributorName, recipeReturned.getContributorName());
    }

}
