package com.epam.final_task.model.entity;

import java.math.BigDecimal;

public class Client extends User {
    private BigDecimal cash;

    public Client(int id, String login, String password, String name, String surname, BigDecimal cash, Role role) {
        super(id, login, password, name, surname, role);
        this.cash = cash;
    }

    public Client(int id, String login, String password, String name, String surname, Role role){
        super(id, login, password, name, surname, role);
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getCash() {
        return cash;
    }
}
