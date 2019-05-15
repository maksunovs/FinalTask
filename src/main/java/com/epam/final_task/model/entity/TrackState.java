package com.epam.final_task.model.entity;

public enum TrackState {
    PURCHASED("purchased"),
    ORDERED("ordered"),
    IN_STORE("in_store");

    private String value;

    TrackState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
