package com.welt.data.controller.errors.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class ErrorDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private ZonedDateTime timeStamp = ZonedDateTime.now();

    private Integer status;

    private String error;

    private String message;

    public ErrorDto() {

    }

    public ErrorDto(Integer status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(ZonedDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
