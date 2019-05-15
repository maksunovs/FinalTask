package com.epam.final_task.model.entity;

public class UserTrack {

    private Integer id;
    private Integer userId;
    private Integer trackId;

    public UserTrack(Integer id, Integer userId, Integer trackId) {
        this.id = id;
        this.userId = userId;
        this.trackId = trackId;
    }

    public UserTrack(Integer userId, Integer trackId) {
        this.userId = userId;
        this.trackId = trackId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getTrackId() {
        return trackId;
    }
}
