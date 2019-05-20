package com.epam.final_task.model.dao.impl;

import com.epam.final_task.builder.Builder;
import com.epam.final_task.model.dao.AbstractDao;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.entity.UserTrack;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class UserTrackDao extends AbstractDao<UserTrack> {
    private static final String TABLE_NAME = "users_tracks";
    private static final String FIND_BY_USER_ID_AND_TARCK_ID = "SELECT *FROM users_tracks WHERE user_id=? AND track_id=?;";
    public UserTrackDao(Connection connection, Builder<UserTrack> builder) {
        super(connection, builder);
    }

    public Optional<UserTrack> findRelationByUserIdAndTrackId(int userId, int trackId) throws DaoException {
        return executeQueryAsSingleResult(FIND_BY_USER_ID_AND_TARCK_ID,userId,trackId);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Map<String, Object> getParameters(UserTrack userTrack) {
        Map<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("id",userTrack.getId());
        parameters.put("user_id",userTrack.getUserId());
        parameters.put("track_id",userTrack.getTrackId());
        return parameters;
    }
}
