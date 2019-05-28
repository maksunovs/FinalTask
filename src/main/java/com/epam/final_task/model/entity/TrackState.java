package com.epam.final_task.model.entity;

public enum TrackState {
    PURCHASED("purchased"),
    IN_CART("in_cart"),
    IN_STORE("in_store");

    private String value;

    TrackState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
