package com.onewingsoft.corestudio.security.filters;

import com.onewingsoft.corestudio.security.auth.jwt.JWTAuthenticationToken;
import com.onewingsoft.corestudio.security.auth.jwt.extractor.TokenExtractor;
import com.onewingsoft.corestudio.security.model.JWTRawAccessToken;
import com.onewingsoft.corestudio.security.model.JWTTokenFactory;
import com.onewingsoft.corestudio.security.config.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTTokenFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationFailureHandler failureHandler;
    private final JWTTokenFactory JWTTokenFactory;
    private final TokenExtractor tokenExtractor;

    @Autowired
    public JWTTokenFilter(AuthenticationFailureHandler failureHandler, JWTTokenFactory JWTTokenFactory,
            TokenExtractor tokenExtractor, RequestMatcher matcher) {
        super(matcher);
        this.failureHandler = failureHandler;
        this.JWTTokenFactory = JWTTokenFactory;
        this.tokenExtractor = tokenExtractor;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String tokenPayload = request.getHeader(WebSecurityConfig.HEADER_STRING);

        JWTRawAccessToken token = new JWTRawAccessToken(tokenExtractor.extract(tokenPayload));

        return getAuthenticationManager().authenticate(new JWTAuthenticationToken(token));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {

        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
