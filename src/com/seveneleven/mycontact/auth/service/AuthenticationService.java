package com.seveneleven.mycontact.auth.service;

import com.seveneleven.mycontact.auth.provider.AuthenticationProvider;
import com.seveneleven.mycontact.auth.session.SessionManager;
import com.seveneleven.mycontact.user.model.User;

public class AuthenticationService {

    private final AuthenticationProvider provider;
    private final SessionManager sessionManager;

    public AuthenticationService(AuthenticationProvider provider,
                                 SessionManager sessionManager) {
        this.provider = provider;
        this.sessionManager = sessionManager;
    }

    public void login(String identifier, String credential) {

        User user = provider.authenticate(identifier, credential);
        sessionManager.login(user);
    }

    public void logout() {
        sessionManager.logout();
    }
}