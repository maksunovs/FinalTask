package com.epam.final_task.model.dao.impl;

import com.epam.final_task.builder.Builder;
import com.epam.final_task.model.dao.AbstractDao;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.entity.Order;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class OrderDao extends AbstractDao<Order> {

    private static final String TABLE_NAME ="orders";
    private static final String FIND_BU_USER_ID = "SELECT *FROM orders WHERE user_id=?;";

    public OrderDao(Connection connection, Builder<Order> builder) {
        super(connection, builder);
    }

    public Optional<Order>  findByUserId(int id) throws DaoException {
        return executeQueryAsSingleResult(FIND_BU_USER_ID,id);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Map<String, Object> getParameters(Order order) {
        Map<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("user_id",order.getUserId());
        return parameters;
    }
}
