package com.epam.final_task.model.entity;

import org.omg.PortableInterceptor.INACTIVE;

import java.math.BigDecimal;
import java.sql.Date;

public class Order {
    private Integer id;
    private Integer userId;
    private BigDecimal value;

    public Order(Integer userId, BigDecimal value) {
        this.userId = userId;
        this.value = value;
    }

    public Order(Integer id, Integer userId, BigDecimal value) {
        this.id = id;
        this.userId = userId;
        this.value = value;
    }

    public Integer getUserId() {
        return userId;
    }

    public BigDecimal getValue() {
        return value;
    }


    public Integer getId() {
        return id;
    }
}
