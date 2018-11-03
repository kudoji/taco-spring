package com.kudoji.taco.data;

import com.kudoji.taco.Order;

public interface OrderRepository {
    boolean save(Order order);
}
