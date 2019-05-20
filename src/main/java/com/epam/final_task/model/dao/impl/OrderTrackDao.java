package com.epam.final_task.model.dao.impl;

import com.epam.final_task.builder.Builder;
import com.epam.final_task.model.dao.AbstractDao;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.entity.OrderTrack;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderTrackDao extends AbstractDao<OrderTrack> {

    private static final String TABLE_NAME = "orders_tracks";
    private static final String FIND_BY_ORDER_ID = "SELECT *FROM orders_tracks WHERE order_id=?;";
    private static final String FIND_BY_ORDER_ID_AND_TRACK_ID = "SELECT *FROM orders_tracks WHERE order_id=? AND track_id=?;";
    public OrderTrackDao(Connection connection, Builder<OrderTrack> builder) {
        super(connection, builder);
    }

    public List<OrderTrack> findByOrderId(int orderId) throws DaoException {
        return executeQuery(FIND_BY_ORDER_ID, orderId);
    }
    public Optional<OrderTrack> findByOrderIdAndTrackId(int orderId, int trackId) throws DaoException {
        return  executeQueryAsSingleResult(FIND_BY_ORDER_ID_AND_TRACK_ID,orderId,trackId);
    }
    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Map<String, Object> getParameters(OrderTrack orderTrack) {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("id", orderTrack.getId());
        parameters.put("order_id", orderTrack.getOrderId());
        parameters.put("track_id", orderTrack.getTrackId());
        return parameters;
    }
}
