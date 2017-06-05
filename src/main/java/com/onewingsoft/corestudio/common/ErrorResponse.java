package com.onewingsoft.corestudio.common;

import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * Created by natete on 04/06/17.
 */
public class ErrorResponse {

    private final HttpStatus status;
    private final String message;
    private final ErrorCode errorCode;
    private final Date timestamp;

    public ErrorResponse(final HttpStatus status, final String message,final ErrorCode errorCode) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = new Date();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
