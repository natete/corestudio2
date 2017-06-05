package com.onewingsoft.corestudio.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by natete on 01/06/17.
 */
@Component
public class JwtSettings {

    @Value("${app.name}")
    private String APP_NAME;

    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.tokenExpirationTime}")
    private int EXPIRES_IN;

    @Value("${jwt.refreshTokenExpirationTime}")
    private int REFRESH_EXPIRES_IN;

    public String getAppName() {
        return APP_NAME;
    }

    public String getSecret() {
        return SECRET;
    }

    public int getExpiresIn() {
        return EXPIRES_IN;
    }

    public int getRefreshExpiresIn() {
        return REFRESH_EXPIRES_IN;
    }
}
