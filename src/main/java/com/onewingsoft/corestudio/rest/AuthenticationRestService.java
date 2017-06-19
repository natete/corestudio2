package com.onewingsoft.corestudio.rest;

import com.onewingsoft.corestudio.business.AuthenticationBusinessLogic;
import com.onewingsoft.corestudio.model.Person;
import com.onewingsoft.corestudio.security.auth.jwt.extractor.TokenExtractor;
import com.onewingsoft.corestudio.security.config.WebSecurityConfig;
import com.onewingsoft.corestudio.security.handlers.JWTAuthenticationSuccessHandler;
import com.onewingsoft.corestudio.security.model.JWTToken;
import com.onewingsoft.corestudio.utils.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Ignacio González Bullón - <nacho.gonzalez.bullon@gmail.com>
 * @since 10/03/16.
 */
@RestController
@RequestMapping("api/auth")
public class AuthenticationRestService {

    @Autowired
    private AuthenticationBusinessLogic authenticationBusinessLogic;

    @Autowired
    private TokenExtractor tokenExtractor;

    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public ResponseEntity<JWTToken> refreshToken(HttpServletRequest request, HttpServletResponse response,
            @RequestBody final Map<String, String> payload) {
        //        String tokenPayload = tokenExtractor.extract(refreshToken);

        try {
            JWTToken token = authenticationBusinessLogic
                    .refreshToken(payload.get(JWTAuthenticationSuccessHandler.REFRESH_TOKEN));

            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            LoggerUtil.writeErrorLog(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<Object> revokeToken(HttpServletRequest request, @RequestBody final String refreshToken) {
        String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.HEADER_STRING));

        try {
            authenticationBusinessLogic.revokeToken(tokenPayload, refreshToken);
            return ResponseEntity.ok().body(null);
        } catch (Exception e) {
            LoggerUtil.writeErrorLog(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public ResponseEntity<Person> getAccount() {

        try {
            Person person = authenticationBusinessLogic.getAccount();
            return ResponseEntity.ok().body(person);
        } catch (Exception e) {
            LoggerUtil.writeErrorLog(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    public String getUri() {
        return "/api/password";
    }
}
