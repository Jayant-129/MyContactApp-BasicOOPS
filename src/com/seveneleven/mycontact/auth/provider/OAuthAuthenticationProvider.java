package com.seveneleven.mycontact.auth.provider;

import com.seveneleven.mycontact.user.model.User;

public class OAuthAuthenticationProvider implements AuthenticationProvider {

    @Override
    public User authenticate(String providerId, String token) {

        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Invalid OAuth token");
        }

        throw new UnsupportedOperationException("OAuth not implemented yet");
    }
}