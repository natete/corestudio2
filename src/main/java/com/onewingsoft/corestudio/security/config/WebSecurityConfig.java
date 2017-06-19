package com.onewingsoft.corestudio.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onewingsoft.corestudio.security.RestAuthenticationEntryPoint;
import com.onewingsoft.corestudio.security.auth.LoginAuthenticationProvider;
import com.onewingsoft.corestudio.security.auth.TokenAuthenticationProvider;
import com.onewingsoft.corestudio.security.auth.jwt.extractor.TokenExtractor;
import com.onewingsoft.corestudio.security.filters.JWTLoginFilter;
import com.onewingsoft.corestudio.security.filters.JWTTokenFilter;
import com.onewingsoft.corestudio.security.matchers.SkipPathRequestMatcher;
import com.onewingsoft.corestudio.security.model.JWTTokenFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String HEADER_STRING = "Authorization";
    private static final String LOGIN_ENDPOINT = "/api/auth/login";
    private static final String REFRESH_TOKEN_ENDPOINT = "/api/auth/refresh";
    private static final String TOKEN_BASED_ENDPOINTS = "/api/**";

    @Autowired
    private LoginAuthenticationProvider loginAuthenticationProvider;

    @Autowired
    private TokenAuthenticationProvider tokenAuthenticationProvider;

    @Autowired
    private JWTTokenFactory tokenFactory;

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RestAuthenticationEntryPoint authenticationEntrypoint;

    @Autowired
    private TokenExtractor tokenExtractor;

    @Autowired
    private CorsFilter corsFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint(this.authenticationEntrypoint)

            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS, TOKEN_BASED_ENDPOINTS).permitAll()

            .and()
            .authorizeRequests()
            .antMatchers(LOGIN_ENDPOINT).permitAll()
            .antMatchers(REFRESH_TOKEN_ENDPOINT).permitAll()

            .and()
            .authorizeRequests()
            .antMatchers("/index.html").permitAll()

            .and()
            .authorizeRequests()
            .antMatchers(TOKEN_BASED_ENDPOINTS).authenticated()

            .and()
            .addFilterBefore(corsFilter, ChannelProcessingFilter.class)
            .addFilterBefore(buildJwtLoginFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(buildJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(loginAuthenticationProvider);
        auth.authenticationProvider(tokenAuthenticationProvider);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private JWTLoginFilter buildJwtLoginFilter() throws Exception {
        JWTLoginFilter filter = new JWTLoginFilter(LOGIN_ENDPOINT, successHandler, failureHandler, mapper);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    private JWTTokenFilter buildJwtTokenFilter() throws Exception {

        List<String> pathsToSkip = Arrays.asList(LOGIN_ENDPOINT, REFRESH_TOKEN_ENDPOINT);
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, TOKEN_BASED_ENDPOINTS);

        JWTTokenFilter filter = new JWTTokenFilter(failureHandler, tokenFactory, tokenExtractor, matcher);

        filter.setAuthenticationManager(this.authenticationManager);

        return filter;
    }
}
