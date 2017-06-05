package com.onewingsoft.corestudio.security.model;

import com.onewingsoft.corestudio.model.RegisteredUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserContext {

    private final String username;
    private final Collection<RegisteredUser.CorestudioRole> authorities;

    private UserContext(String username, Collection<RegisteredUser.CorestudioRole> authorities) {
        this.username = username;
        this.authorities = authorities;
    }
    
    public static UserContext create(String username, Collection<RegisteredUser.CorestudioRole> authorities) {
        if (StringUtils.isBlank(username)) throw new IllegalArgumentException("Username is blank: " + username);
        return new UserContext(username, authorities);
    }

    public String getUsername() {
        return username;
    }

    public Collection<RegisteredUser.CorestudioRole> getAuthorities() {
        return authorities;
    }
}
