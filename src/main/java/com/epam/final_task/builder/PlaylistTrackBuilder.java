//package com.epam.final_task.builder;
//
//import com.epam.final_task.model.entity.PlaylistTrack;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class PlaylistTrackBuilder implements Builder<PlaylistTrack> {
//    @Override
//    public PlaylistTrack build(ResultSet resultSet) throws  SQLException {
//        int id = resultSet.getInt("id");
//        int playlistId = resultSet.getInt("playlist_id");
//        int audiotrackId = resultSet.getInt("track_id");
//        return new PlaylistTrack(id,playlistId,audiotrackId);
//    }
//}
