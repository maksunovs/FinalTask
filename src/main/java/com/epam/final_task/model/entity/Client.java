package com.epam.final_task.model.entity;

import com.epam.final_task.model.entity.enums.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "users")
public class Client extends User {

    private BigDecimal cash;

    public Client(int id, String login, String password, String name, String surname, BigDecimal cash, Role role) {
        super(id, login, password, name, surname, role);
        this.cash = cash;
    }

    public Client(int id, String login, String password, String name, String surname, Role role) {
        super(id, login, password, name, surname, role);
    }




}

