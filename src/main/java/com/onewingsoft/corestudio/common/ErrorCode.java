package com.onewingsoft.corestudio.common;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by natete on 04/06/17.
 */
public enum ErrorCode {
    AUTHENTICATION(10), TOKEN_EXPIRED(11);

    private int errorCode;

    private ErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonValue
    public int getErrorCode() {
        return errorCode;
    }
}
