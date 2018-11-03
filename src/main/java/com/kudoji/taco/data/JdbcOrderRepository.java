package com.kudoji.taco.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kudoji.taco.Order;
import com.kudoji.taco.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository {
    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderTacoInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate){
        this.orderInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("orders")
                .usingGeneratedKeyColumns("id");

        this.orderTacoInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("orders_tacos");

        this.objectMapper = new ObjectMapper();
    }

    @Override
    public boolean save(Order order) {
        order.setPlacedAt(new Date());

        long orderId = saveOrder(order);

        if (orderId < 1) return false;
        order.setId(orderId);

        List<Taco> tacos = order.getTacos();
        for (Taco taco: tacos){
            if (!saveTacoToOrder(taco, orderId)){
                return false;
            }
        }

        return true;
    }

    private long saveOrder(Order order){
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);

        values.put("placedAt", order.getPlacedAt());

        long orderId = orderInserter
                .executeAndReturnKey(values)
                .longValue();

        return orderId;
    }

    private boolean saveTacoToOrder(Taco taco, long orderId){
        Map<String, Object> values = new HashMap<>();

        values.put("tacos_id", taco.getId());
        values.put("orders_id", orderId);

        return (this.orderTacoInserter.execute(values) > 0);
    }
}
