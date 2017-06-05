package com.onewingsoft.corestudio.security.auth.jwt.extractor;

public interface TokenExtractor {
    String extract(String payload);
}
