package com.epam.final_task.model.entity;



public class Order {
    private Integer id;
    private Integer userId;

    public Order(Integer userId) {
        this.userId = userId;
    }

    public Order(Integer id, Integer userId) {
        this.id = id;
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }


    public Integer getId() {
        return id;
    }
}
