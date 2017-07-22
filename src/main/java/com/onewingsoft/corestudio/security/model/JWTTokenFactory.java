package com.onewingsoft.corestudio.security.model;

import com.onewingsoft.corestudio.model.RegisteredUser;
import com.onewingsoft.corestudio.security.config.JwtSettings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JWTTokenFactory {

    public static final String ROLES_KEY = "roles";

    @Autowired
    private JwtSettings jwtSettings;

    private static SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public JWTAccessToken createAccessToken(UserContext user) {
        if (StringUtils.isBlank(user.getUsername())) {
            throw new IllegalArgumentException("No se puede crear token sin nombre de usuario");
        }

        if (user.getAuthorities() == null || user.getAuthorities().isEmpty()) {
            throw new IllegalArgumentException("El usuario no tiene privilegios");
        }

        LocalDateTime currentTime = LocalDateTime.now();

        Claims claims = Jwts.claims().setSubject(user.getUsername());

        claims.put(ROLES_KEY, user.getAuthorities().stream().map(s -> s.getAuthority()).collect(Collectors.toList()));

        claims.setExpiration(Date.from(currentTime.plusMinutes(jwtSettings.getExpiresIn())
                                                  .atZone(ZoneId.systemDefault()).toInstant()));

        claims.setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()));

        String jwt = Jwts.builder()
                         .setIssuer(jwtSettings.getAppName())
                         .setClaims(claims)
                         .signWith(SIGNATURE_ALGORITHM, jwtSettings.getSecret())
                         .compact();

        return new JWTAccessToken(jwt, claims);
    }

    public JWTToken createRefreshToken(UserContext user) {
        if (StringUtils.isBlank(user.getUsername())) {
            throw new IllegalArgumentException("No se puede crear token sin nombre de usuario");
        }

        LocalDateTime currentTime = LocalDateTime.now();

        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put(ROLES_KEY, Collections.singletonList(RegisteredUser.CorestudioRole.REFRESH.getAuthority()));

        claims.setExpiration(Date.from(currentTime.plusMinutes(jwtSettings.getRefreshExpiresIn())
                                                  .atZone(ZoneId.systemDefault()).toInstant()));
        claims.setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()));
        claims.setId(UUID.randomUUID().toString());

        String jwt = Jwts.builder()
                         .setIssuer(jwtSettings.getAppName())
                         .setClaims(claims)
                         .signWith(SIGNATURE_ALGORITHM, jwtSettings.getSecret())
                         .compact();

        return new JWTAccessToken(jwt, claims);
    }
}
