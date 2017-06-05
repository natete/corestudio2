package com.onewingsoft.corestudio.security.model;

import java.util.Date;

public class RevokedToken {

    private final String jti;
    private final Date expirationDate;

    public RevokedToken(String jti, Date expirationDate) {
        this.jti = jti;
        this.expirationDate = expirationDate;
    }

    public String getJti() {
        return jti;
    }

    public boolean isExpired() {
        return expirationDate.before(new Date(System.currentTimeMillis()));
    }
}
