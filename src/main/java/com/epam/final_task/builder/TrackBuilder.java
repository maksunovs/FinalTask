package com.epam.final_task.builder;

import com.epam.final_task.model.entity.Track;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackBuilder implements  Builder<Track> {
    @Override
    public Track build(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String artist = resultSet.getString("name");
        String title = resultSet.getString("title");
        String genre = resultSet.getString("genre");
        BigDecimal price = resultSet.getBigDecimal("price");
        int albumId = resultSet.getInt("album_id");
        int artistId = resultSet.getInt("artist_id");
        return  new Track(id,artist,artistId,title,albumId,genre,price);
    }
}
