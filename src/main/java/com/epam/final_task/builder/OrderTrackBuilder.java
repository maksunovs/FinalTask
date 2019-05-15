package com.epam.final_task.builder;

import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.entity.OrderTrack;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderTrackBuilder implements Builder<OrderTrack> {
    @Override
    public OrderTrack build(ResultSet resultSet) throws DaoException, SQLException {
        int id = resultSet.getInt("id");
        int orderId = resultSet.getInt("order_id");
        int trackId = resultSet.getInt("track_id");
        return new OrderTrack(id, orderId, trackId);
    }
}
