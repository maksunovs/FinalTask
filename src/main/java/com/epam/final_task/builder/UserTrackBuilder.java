package com.epam.final_task.builder;

import com.epam.final_task.model.entity.UserTrack;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTrackBuilder implements Builder<UserTrack> {
    @Override
    public UserTrack build(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        int trackId = resultSet.getInt("track_id");
        return new UserTrack(id,userId,trackId);
    }
}
