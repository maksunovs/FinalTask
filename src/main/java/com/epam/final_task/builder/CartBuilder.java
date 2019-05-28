package com.epam.final_task.builder;

import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.entity.Cart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartBuilder implements Builder<Cart>{
    @Override
    public Cart build(ResultSet resultSet) throws DaoException, SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        return new Cart(id,userId);
    }
}
