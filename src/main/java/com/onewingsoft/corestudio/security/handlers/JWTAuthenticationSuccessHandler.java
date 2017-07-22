package com.onewingsoft.corestudio.security.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onewingsoft.corestudio.security.model.JWTToken;
import com.onewingsoft.corestudio.security.model.JWTTokenFactory;
import com.onewingsoft.corestudio.security.model.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    public static final String TOKEN = "token";
    public static final String REFRESH_TOKEN = "refreshToken";
    private final ObjectMapper mapper;
    private final JWTTokenFactory tokenFactory;

    @Autowired
    public JWTAuthenticationSuccessHandler(ObjectMapper mapper, JWTTokenFactory tokenFactory) {
        this.mapper = mapper;
        this.tokenFactory = tokenFactory;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
            throws IOException, ServletException {

        UserContext userContext = (UserContext) auth.getPrincipal();

        JWTToken accessToken = tokenFactory.createAccessToken(userContext);
        JWTToken refreshToken = tokenFactory.createRefreshToken(userContext);

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put(TOKEN, accessToken.getToken());
        tokenMap.put(REFRESH_TOKEN, refreshToken.getToken());

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getWriter(), tokenMap);

        clearAuthenticationAttributes(request);
    }

    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
