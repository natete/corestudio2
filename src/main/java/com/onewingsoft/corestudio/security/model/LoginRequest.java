package com.onewingsoft.corestudio.security.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by natete on 01/06/17.
 */
public class LoginRequest {

    private String username;
    private String password;

    @JsonCreator
    public LoginRequest(@JsonProperty("username") String username, @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
