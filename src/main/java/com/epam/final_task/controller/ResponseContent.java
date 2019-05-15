package com.epam.final_task.controller;

import com.epam.final_task.model.entity.ResponseType;

public class ResponseContent {
    private final ResponseType responseType;
    private final String contentPath;

    public ResponseContent(ResponseType responseType, String contentPath) {
        this.responseType = responseType;
        this.contentPath = contentPath;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public String getContentPath() {
        return contentPath;
    }
}
