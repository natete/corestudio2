package com.onewingsoft.corestudio.business;

import com.onewingsoft.corestudio.model.Person;
import com.onewingsoft.corestudio.model.RegisteredUser;
import com.onewingsoft.corestudio.repository.RegisteredUserRepository;
import com.onewingsoft.corestudio.security.SecurityUtils;
import com.onewingsoft.corestudio.security.auth.jwt.verifier.TokenVerifier;
import com.onewingsoft.corestudio.security.config.JwtSettings;
import com.onewingsoft.corestudio.security.exceptions.JwtInvalidToken;
import com.onewingsoft.corestudio.security.handlers.JWTAuthenticationSuccessHandler;
import com.onewingsoft.corestudio.security.model.JWTRawAccessToken;
import com.onewingsoft.corestudio.security.model.JWTRefreshToken;
import com.onewingsoft.corestudio.security.model.JWTTokenFactory;
import com.onewingsoft.corestudio.security.model.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationBusinessLogic {

    @Autowired
    private TokenVerifier tokenVerifier;

    @Autowired
    private JwtSettings jwtSettings;

    @Autowired
    private JWTTokenFactory tokenFactory;

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Autowired
    private PersonBusinessLogic personBusinessLogic;

    public Map<String, String> refreshToken(String tokenPayload) throws Exception {
        Map<String, String> result = new HashMap<>();
        JWTRawAccessToken rawAccessToken = new JWTRawAccessToken(tokenPayload);
        JWTRefreshToken refreshToken = JWTRefreshToken.create(rawAccessToken, jwtSettings.getSecret())
                                                      .orElseThrow(JwtInvalidToken::new);

        String jti = refreshToken.getJti();

        if (!tokenVerifier.verify(jti)) {
            throw new JwtInvalidToken();
        }

        String subject = refreshToken.getSubject();
        RegisteredUser registeredUser = registeredUserRepository.findByUsername(subject);

        if (registeredUser == null) {
            throw new UsernameNotFoundException("El usuario no existe");
        }

        UserContext userContext = UserContext.create(registeredUser.getUsername(), registeredUser.getAuthorities());

        result.put(JWTAuthenticationSuccessHandler.REFRESH_TOKEN, tokenFactory.createRefreshToken(userContext)
                                                                              .getToken());
        result.put(JWTAuthenticationSuccessHandler.TOKEN, tokenFactory.createAccessToken(userContext).getToken());
        return result;
    }

    public void revokeToken(String tokenPayload, String refreshTokenPayload) {
        JWTRawAccessToken rawRefreshToken = new JWTRawAccessToken(refreshTokenPayload);

        JWTRawAccessToken rawAccessToken = new JWTRawAccessToken(tokenPayload);
        String subject = rawAccessToken.parseClaims(jwtSettings.getSecret()).getBody().getSubject();

        JWTRefreshToken refreshToken = JWTRefreshToken.create(rawRefreshToken, jwtSettings.getSecret())
                                                      .orElseThrow(JwtInvalidToken::new);

        if (refreshToken.getSubject().equals(subject)) {
            tokenVerifier.revokeToken(refreshToken.getJti(), refreshToken.getExpirationDate());
        } else {
            throw new JwtInvalidToken();
        }
    }

    public Person getAccount() {
        UserContext user = SecurityUtils.getCurrentUser();

        String username = user.getUsername();

        RegisteredUser.CorestudioRole role = user.getAuthorities().stream().findFirst().get();

        Person person = personBusinessLogic.findPerson(username, role);

        if (person == null) {
            throw new UsernameNotFoundException("El usuario no existe");
        }

        return person;
    }
}
