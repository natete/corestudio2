package com.onewingsoft.corestudio.security.model;

import com.onewingsoft.corestudio.model.RegisteredUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class JWTRefreshToken implements JWTToken {

    private Jws<Claims> claims;

    private JWTRefreshToken(Jws<Claims> claims) {
        this.claims = claims;
    }

    public static Optional<JWTRefreshToken> create(JWTRawAccessToken token, String signingKey) {
        Jws<Claims> claims = token.parseClaims(signingKey);
        List<String> roles = claims.getBody().get(JWTTokenFactory.ROLES_KEY, List.class);

        if (roles == null || roles.isEmpty() || !hasRefreshTokenRole(roles)) {
            return Optional.empty();
        }

        return Optional.of(new JWTRefreshToken(claims));
    }

    @Override
    public String getToken() {
        return null;
    }

    public Jws<Claims> getClaims() {
        return claims;
    }

    public String getJti() {
        return claims.getBody().getId();
    }

    public String getSubject() {
        return claims.getBody().getSubject();
    }

    public Date getExpirationDate() {
        return claims.getBody().getExpiration();
    }

    private static boolean hasRefreshTokenRole(List<String> roles) {
        return roles.stream().anyMatch(role -> RegisteredUser.CorestudioRole.REFRESH.getAuthority().equals(role));
    }
}
