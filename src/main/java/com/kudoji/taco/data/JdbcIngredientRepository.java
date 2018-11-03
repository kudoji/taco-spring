package com.kudoji.taco.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcIngredientRepository {
    private IngredientRepository ingredientRepository;

    @Autowired
    public JdbcIngredientRepository(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }
}