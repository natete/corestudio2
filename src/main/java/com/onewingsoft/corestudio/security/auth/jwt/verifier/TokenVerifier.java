package com.onewingsoft.corestudio.security.auth.jwt.verifier;

import java.util.Date;

/**
 * Created by natete on 04/06/17.
 */
public interface TokenVerifier {
    boolean verify(String jti);

    void revokeToken(String jti, Date expirationDate);
}
