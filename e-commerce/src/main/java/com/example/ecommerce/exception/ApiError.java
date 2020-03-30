package com.example.ecommerce.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Date;

public class ApiError
{
    private HttpStatus httpStatus;
    private Date timestamp;
    private String message;
    private String debugMessage;

    public ApiError()
    {

    }

    public ApiError(Date timestamp, String message, String debugMessage) {
        this.timestamp = timestamp;
        this.message = message;
        this.debugMessage = debugMessage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
