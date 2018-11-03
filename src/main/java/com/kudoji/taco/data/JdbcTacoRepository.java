package com.kudoji.taco.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTacoRepository {
    private TacoRepository tacoRepository;

    @Autowired
    public JdbcTacoRepository(TacoRepository tacoRepository){
        this.tacoRepository = tacoRepository;
    }
}