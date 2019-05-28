package com.epam.final_task.builder;

import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.entity.CartTrack;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartTrackBuilder implements Builder<CartTrack> {
    @Override
    public CartTrack build(ResultSet resultSet) throws DaoException, SQLException {
        int id = resultSet.getInt("id");
        int cartId = resultSet.getInt("order_id");
        int trackId = resultSet.getInt("track_id");
        return new CartTrack(id, cartId, trackId);
    }
}
