package com.seveneleven.mycontact.auth.provider;

import com.seveneleven.mycontact.user.model.User;

public interface AuthenticationProvider {

    User authenticate(String identifier, String credential);
}