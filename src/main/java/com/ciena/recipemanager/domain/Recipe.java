package com.ciena.recipemanager.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Recipes")
public class Recipe {
    @Id
    private String id;
    private String contributorName;
    private String title;
    private String instructions;
    private String notes;
}
