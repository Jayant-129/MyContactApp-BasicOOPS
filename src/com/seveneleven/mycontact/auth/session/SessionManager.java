package com.seveneleven.mycontact.auth.session;

import com.seveneleven.mycontact.user.model.User;

public class SessionManager {

    private User currentUser;

    public void login(User user) {
        this.currentUser = user;
    }

    public void logout() {
        this.currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
}