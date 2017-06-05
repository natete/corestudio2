package com.onewingsoft.corestudio.security.auth.jwt.verifier;

import com.onewingsoft.corestudio.security.model.RevokedToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BloomTokenVerifier implements TokenVerifier {

    private List<RevokedToken> revokedTokens = new ArrayList<>();

    @Override
    public boolean verify(String jti) {
        clearExpiredTokens();

        return revokedTokens.stream().noneMatch(revokedToken -> revokedToken.getJti().equals(jti));
    }

    @Override
    public void revokeToken(String jti, Date expirationDate) {
        clearExpiredTokens();
        revokedTokens.add(new RevokedToken(jti, expirationDate));
    }

    private void clearExpiredTokens() {
        revokedTokens = revokedTokens.stream()
                .filter(revokedToken -> !revokedToken.isExpired())
                .collect(Collectors.toList());
    }
}
