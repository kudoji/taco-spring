package com.kudoji.taco.data;

import com.kudoji.taco.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
