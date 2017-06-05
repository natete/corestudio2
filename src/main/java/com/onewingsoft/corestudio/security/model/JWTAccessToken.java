package com.onewingsoft.corestudio.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;

public class JWTAccessToken implements JWTToken {

    private final String token;
    @JsonIgnore
    private Claims claims;

    protected JWTAccessToken(String token, Claims claims) {
        this.token = token;
        this.claims = claims;
    }

    @Override
    public String getToken() {
        return this.token;
    }

    public Claims getClaims() {
        return this.claims;
    }
}
