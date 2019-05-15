package com.epam.final_task.builder;

import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.entity.Order;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderBuilder implements Builder<Order>{
    @Override
    public Order build(ResultSet resultSet) throws DaoException, SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        BigDecimal value = resultSet.getBigDecimal("value");
        return new Order(id,userId,value);
    }
}
