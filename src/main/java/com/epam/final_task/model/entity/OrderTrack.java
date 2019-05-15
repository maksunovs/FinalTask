package com.epam.final_task.model.entity;

public class OrderTrack {
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
}
