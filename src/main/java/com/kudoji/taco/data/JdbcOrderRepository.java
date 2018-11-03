package com.kudoji.taco.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcOrderRepository{
    private OrderRepository orderRepository;

    @Autowired
    public JdbcOrderRepository(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
}