package com.epam.final_task.model.entity;

public class Admin extends User {
    public Admin(int id, String login, String password, String name, String surname, Role role) {
        super(id, login, password, name, surname, role);
    }

    @Override
    public String toString() {
        return "Admin{" +
                super.toString()+
                '}';
    }
}
