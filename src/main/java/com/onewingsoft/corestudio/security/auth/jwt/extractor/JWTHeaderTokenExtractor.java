package com.onewingsoft.corestudio.security.auth.jwt.extractor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

@Component
public class JWTHeaderTokenExtractor implements TokenExtractor {

    private static final String HEADER_PREFIX = "Bearer ";

    @Override
    public String extract(String header) {
        if (StringUtils.isBlank(header)) {
            throw new AuthenticationServiceException("La cabecera de autorización no puede estar en blanco");
        }

        if (header.length() < HEADER_PREFIX.length()) {
            throw new AuthenticationServiceException("El tamaño de la cabecera de autorización no es válido");
        }

        return header.substring(HEADER_PREFIX.length(), header.length());
    }
}
