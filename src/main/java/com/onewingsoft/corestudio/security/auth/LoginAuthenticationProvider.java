package com.onewingsoft.corestudio.security.auth;

import com.onewingsoft.corestudio.model.RegisteredUser;
import com.onewingsoft.corestudio.repository.RegisteredUserRepository;
import com.onewingsoft.corestudio.security.model.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider{

    private final BCryptPasswordEncoder encoder;
    private final RegisteredUserRepository registeredUserRepository;

    @Autowired
    public LoginAuthenticationProvider(final BCryptPasswordEncoder encoder, final RegisteredUserRepository registeredUserRepository) {
        this.encoder = encoder;
        this.registeredUserRepository = registeredUserRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No auth data provided");

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        RegisteredUser user = registeredUserRepository.findByUsername(username);

        if (user == null || !encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Usuario o contraseña incorrectas");
        }

        UserContext userContext = UserContext.create(user.getUsername(), user.getAuthorities());

        return new UsernamePasswordAuthenticationToken(userContext, null, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
