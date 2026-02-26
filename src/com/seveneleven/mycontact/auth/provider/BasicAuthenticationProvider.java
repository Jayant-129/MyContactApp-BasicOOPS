package com.seveneleven.mycontact.auth.provider;

import com.seveneleven.mycontact.user.model.User;
import com.seveneleven.mycontact.user.service.PasswordUtil;
import com.seveneleven.mycontacts.user.repository.UserRepository;

public class BasicAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    public BasicAuthenticationProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User authenticate(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!PasswordUtil.matches(password, user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid password");
        }

        return user;
    }
}
