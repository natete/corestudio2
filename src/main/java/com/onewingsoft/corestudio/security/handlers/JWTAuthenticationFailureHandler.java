package com.onewingsoft.corestudio.security.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onewingsoft.corestudio.common.ErrorCode;
import com.onewingsoft.corestudio.common.ErrorResponse;
import com.onewingsoft.corestudio.security.exceptions.AuthMethodNotSupportedException;
import com.onewingsoft.corestudio.security.exceptions.JwtExpiredTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper mapper;

    @Autowired
    public JWTAuthenticationFailureHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException, ServletException {

        ErrorResponse errorResponse;

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);


        if (e instanceof BadCredentialsException) {
            errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, "Usuario o password erróneo", ErrorCode.AUTHENTICATION);
        } else if (e instanceof JwtExpiredTokenException) {
            errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, "Token caducado", ErrorCode.TOKEN_EXPIRED);
        } else if (e instanceof AuthMethodNotSupportedException) {
            errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage(), ErrorCode.AUTHENTICATION);
        } else {
            errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, "Fallo de autenticación", ErrorCode.AUTHENTICATION);
        }

        mapper.writeValue(response.getWriter(), errorResponse);
    }
}
