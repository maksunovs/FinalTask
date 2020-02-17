package com.epam.final_task.builder;

import com.epam.final_task.model.entity.Artist;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtistBuilder implements Builder<Artist> {
    @Override
    public Artist build(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String country = resultSet.getString("country");
        return new Artist(id,name,country);
    }
}
