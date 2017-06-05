package com.onewingsoft.corestudio.security.auth;

import com.onewingsoft.corestudio.model.RegisteredUser;
import com.onewingsoft.corestudio.security.auth.jwt.JWTAuthenticationToken;
import com.onewingsoft.corestudio.security.config.JwtSettings;
import com.onewingsoft.corestudio.security.model.JWTRawAccessToken;
import com.onewingsoft.corestudio.security.model.JWTTokenFactory;
import com.onewingsoft.corestudio.security.model.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private final JwtSettings jwtSettings;

    @Autowired
    public TokenAuthenticationProvider(JwtSettings jwtSettings) {
        this.jwtSettings = jwtSettings;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JWTRawAccessToken rawAccessToken = (JWTRawAccessToken) authentication.getCredentials();

        Jws<Claims> claims = rawAccessToken.parseClaims(jwtSettings.getSecret());

        String subject = claims.getBody().getSubject();
        List<String> roles = claims.getBody().get(JWTTokenFactory.ROLES_KEY, List.class);

        List<RegisteredUser.CorestudioRole> authorities = roles.stream()
                                                               .map(RegisteredUser.CorestudioRole::valueOf)
                                                               .collect(Collectors.toList());

        UserContext context = UserContext.create(subject, authorities);

        return new JWTAuthenticationToken(context, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
