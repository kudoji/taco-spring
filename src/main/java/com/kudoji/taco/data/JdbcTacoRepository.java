package com.kudoji.taco.data;

import com.kudoji.taco.Ingredient;
import com.kudoji.taco.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcTacoRepository implements TacoRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTacoRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Taco save(Taco taco) {
        long tacoId = saveTaco(taco);

        taco.setId(tacoId);

        List<Ingredient> ingredients = taco.getIngredients();
        for (Ingredient ingredient: ingredients){
            saveIngredientToTaco(ingredient, tacoId);
        }

        return taco;
    }

    private long saveTaco(Taco taco){
        taco.setCreatedAt(new Date());

        PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
                "insert into tacos set name = ?, createdAt = ?;",
                Types.VARCHAR, Types.TIMESTAMP).newPreparedStatementCreator(
                    Arrays.asList(
                            taco.getName(),
                            new Timestamp(taco.getCreatedAt().getTime())
                    )
        );

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, long tacoId){
        jdbcTemplate.update(
                "insert into tacos_ingredients set tacos_id = ?, ingredients_id = ?;",
                tacoId, ingredient.getId()
        );
    }
}
