package com.epam.final_task.model.entity;

public class UserTrack extends Entity {

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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        UserTrack userTrack = (UserTrack) object;
        return this.id.equals(userTrack.id) &&
                this.userId.equals(userTrack.userId) &&
                this.trackId.equals(userTrack.trackId);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int hash = 1;
        hash = hash * prime + (id == null ? 0 : id);
        hash = hash * prime + (userId == null ? 0 : userId);
        hash = hash * prime + (trackId == null ? 0 : trackId);
        return hash * prime;
    }

    @Override
    public String toString() {
        return "UserTrack{" +
                "id=" + id +
                ", userId=" + userId +
                ", trackId=" + trackId +
                '}';
    }
}
