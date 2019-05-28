package com.epam.final_task.model.dao.impl;

import com.epam.final_task.builder.Builder;
import com.epam.final_task.model.dao.AbstractDao;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.entity.CartTrack;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CartTrackDao extends AbstractDao<CartTrack> {

    private static final String TABLE_NAME = "orders_tracks";
    private static final String FIND_BY_CART_ID = "SELECT *FROM orders_tracks WHERE order_id=?;";
    private static final String FIND_BY_CART_ID_AND_TRACK_ID = "SELECT *FROM orders_tracks WHERE order_id=? AND track_id=?;";
    public CartTrackDao(Connection connection, Builder<CartTrack> builder) {
        super(connection, builder);
    }

    public List<CartTrack> findByCartId(int cartId) throws DaoException {
        return executeQuery(FIND_BY_CART_ID, cartId);
    }
    public Optional<CartTrack> findByCartIdAndTrackId(int cartId, int trackId) throws DaoException {
        return  executeQueryAsSingleResult(FIND_BY_CART_ID_AND_TRACK_ID,cartId,trackId);
    }
    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Map<String, Object> getParameters(CartTrack cartTrack) {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("id", cartTrack.getId());
        parameters.put("order_id", cartTrack.getCartId());
        parameters.put("track_id", cartTrack.getTrackId());
        return parameters;
    }
}
