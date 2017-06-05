package com.onewingsoft.corestudio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.onewingsoft.corestudio.model.Professor;
import com.onewingsoft.corestudio.model.RegisteredUser;

/**
 * @author Ignacio González Bullón - <nacho.gonzalez.bullon@gmail.com>
 * @since 02/01/16.
 */
public class ProfessorDTO extends Professor {

    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private RegisteredUser.CorestudioRole role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RegisteredUser.CorestudioRole getRole() {
        return role;
    }

    public void setRole(RegisteredUser.CorestudioRole role) {
        this.role = role;
    }
}
