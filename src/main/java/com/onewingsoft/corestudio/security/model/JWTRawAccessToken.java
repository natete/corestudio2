package com.onewingsoft.corestudio.security.model;

import com.onewingsoft.corestudio.security.exceptions.JwtExpiredTokenException;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.BadCredentialsException;

public class JWTRawAccessToken implements JWTToken {
    private String token;

    public JWTRawAccessToken(String token) {
        this.token = token;
    }

    public Jws<Claims> parseClaims(String signingKey) {
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            throw new BadCredentialsException("Token inválido", ex);
        } catch (ExpiredJwtException expiredEx) {
            throw new JwtExpiredTokenException(this, "Token caducado", expiredEx);
        }
    }

    @Override
    public String getToken() {
        return token;
    }
}
