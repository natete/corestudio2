package com.onewingsoft.corestudio.security;

import com.onewingsoft.corestudio.model.RegisteredUser;
import com.onewingsoft.corestudio.security.model.UserContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Ignacio González Bullón - <nacho.gonzalez.bullon@gmail.com>
 * @since 10/03/16.
 */
public class SecurityUtils {

    /**
     * Return the current user, or throws an exception, if the user is not
     * authenticated yet.
     *
     * @return the current user
     */
    public static UserContext getCurrentUser() throws IllegalStateException {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserContext) {
                return (UserContext) authentication.getPrincipal();
            }
        }
        throw new IllegalStateException("User not found!");
    }

    public static RegisteredUser.CorestudioRole getCurrentUserRole() throws IllegalStateException {
        final UserContext userContext = SecurityUtils.getCurrentUser();

        return userContext.getAuthorities().stream().findFirst().get();
    }
}
