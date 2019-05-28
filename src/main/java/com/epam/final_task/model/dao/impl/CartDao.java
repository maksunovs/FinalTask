package com.epam.final_task.model.dao.impl;

import com.epam.final_task.builder.Builder;
import com.epam.final_task.model.dao.AbstractDao;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.entity.Cart;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class CartDao extends AbstractDao<Cart> {

    private static final String TABLE_NAME ="orders";
    private static final String FIND_BU_USER_ID = "SELECT *FROM orders WHERE user_id=?;";

    public CartDao(Connection connection, Builder<Cart> builder) {
        super(connection, builder);
    }

    public Optional<Cart>  findByUserId(int id) throws DaoException {
        return executeQueryAsSingleResult(FIND_BU_USER_ID,id);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Map<String, Object> getParameters(Cart cart) {
        Map<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("user_id", cart.getUserId());
        return parameters;
    }
}
