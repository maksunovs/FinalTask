//package com.epam.final_task.builder;
//
//import com.epam.final_task.model.entity.Album;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class AlbumBuilder implements Builder<Album> {
//    @Override
//    public Album build(ResultSet resultSet) throws SQLException {
//        int id = resultSet.getInt("id");
//        String title = resultSet.getString("title");
//        int artist = resultSet.getInt("artist_id");
//        return new Album(id,title,artist);
//    }
//}
