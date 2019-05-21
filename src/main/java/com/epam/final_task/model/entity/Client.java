package com.epam.final_task.model.entity;

import java.math.BigDecimal;

public class Client extends User {

    private BigDecimal cash;

    public Client(int id, String login, String password, String name, String surname, BigDecimal cash, Role role) {
        super(id, login, password, name, surname, role);
        this.cash = cash;
    }

    public Client(int id, String login, String password, String name, String surname, Role role) {
        super(id, login, password, name, surname, role);
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getCash() {
        return cash;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        Client client = (Client) object;
        return this.cash.equals(client.cash);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int hash = 1;
        hash = hash * prime + super.hashCode();
        hash = hash * prime + (cash == null ? 0 : cash.intValue());
        return hash * prime;
    }

    @Override
    public String toString() {
        return "Client{" +
                "cash=" + cash +
                super.toString() +
                '}';
    }
}
