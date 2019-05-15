package com.epam.final_task.builder;

import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.entity.Playlist;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistBuilder implements Builder<Playlist> {
    @Override
    public Playlist build(ResultSet resultSet) throws DaoException, SQLException {
        Integer id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        return new Playlist(id,title);
    }
}
