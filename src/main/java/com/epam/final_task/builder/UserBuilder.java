package com.epam.final_task.builder;

import com.epam.final_task.model.entity.Admin;
import com.epam.final_task.model.entity.Client;
import com.epam.final_task.model.entity.Role;
import com.epam.final_task.model.entity.User;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {
    @Override
    public User build(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        Role role = Role.valueOf(resultSet.getString("role").toUpperCase());
        BigDecimal cash = resultSet.getBigDecimal("value");
        User user;
        if (role == Role.CLIENT) {
            user = new Client(id, login, password, name, surname, cash, role);
        } else {
            user = new Admin(id, login, password, name, surname, role);
        }
        return user;
    }
}
