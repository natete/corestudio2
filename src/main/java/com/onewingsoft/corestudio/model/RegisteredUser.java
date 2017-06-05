package com.onewingsoft.corestudio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "registered_user")
public class RegisteredUser extends BaseEntity {

    public enum CorestudioRole implements GrantedAuthority {
        ADMIN("ADMIN"), USER("USER"), CLIENT("CLIENT"), REFRESH("REFRESH");

        private String role;

        CorestudioRole(String role) {
            this.role = role;
        }

        @Override
        public String getAuthority() {
            return this.role;
        }
    }

    @Column
    @NotNull
    @Size(min = 1, max = 25)
    private String username;

    @Column
    @NotNull
    @Size(min = 1, max = 60)
    @JsonIgnore
    private String password;

    @Column
    @ElementCollection(targetClass = CorestudioRole.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Collection<CorestudioRole> authorities;

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

    public Collection<CorestudioRole> getAuthorities() {
        return authorities;
    }

    public void setRole(Collection<CorestudioRole> roles) {
        this.authorities = roles;
    }
}
