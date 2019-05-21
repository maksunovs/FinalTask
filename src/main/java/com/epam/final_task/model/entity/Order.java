package com.epam.final_task.model.entity;



public class Order extends Entity {

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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Order order = (Order) object;
        return this.id.equals(order.id) &&
                this.userId.equals(order.id);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int hash = 1;
        hash = hash * prime + (id == null ? 0 : id);
        hash = hash * prime + (userId == null ? 0 : userId);
        return hash * prime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                '}';
    }
}
