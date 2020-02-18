package com.epam.final_task.model.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "orders")
public class Cart implements Serializable {

    private Long id;
    private User user;

    public Cart(User user) {
        this.user = user;
    }


}
