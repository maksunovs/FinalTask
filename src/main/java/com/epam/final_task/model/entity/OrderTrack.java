package com.epam.final_task.model.entity;


public class OrderTrack extends Entity {
    private Integer id;
    private Integer orderId;
    private Integer trackId;

    public OrderTrack(Integer orderId, Integer trackId) {
        this.orderId = orderId;
        this.trackId = trackId;
    }

    public OrderTrack(Integer id, Integer orderId, Integer trackId) {
        this.id = id;
        this.orderId = orderId;
        this.trackId = trackId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getOrderId() {
        return orderId;
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
        OrderTrack orderTrack = (OrderTrack) object;
        return this.id.equals(orderTrack.id) &&
                this.orderId.equals(orderTrack.orderId) &&
                this.trackId.equals(orderTrack.trackId);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int hash = 1;
        hash = hash * prime + (id == null ? 0 : id);
        hash = hash * prime + (orderId == null ? 0 : orderId);
        hash = hash * prime + (trackId == null ? 0 : trackId);
        return hash * prime;
    }

    @Override
    public String toString() {
        return "OrderTrack{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", trackId=" + trackId +
                '}';
    }
}
