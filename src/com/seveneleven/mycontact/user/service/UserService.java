package com.seveneleven.mycontact.user.service;

import java.util.Optional;
import com.seveneleven.mycontact.user.model.*;
import com.seveneleven.mycontacts.user.repository.UserRepository;

public class UserService {
	
    private final UserRepository repository;
    private final UserValidatorService validator;

    public UserService(UserRepository repository,
                       UserValidatorService validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public User register(String email,
                         String password,
                         String name,
                         UserType type) {

        validator.validateForRegistration(email, password, name);

        Optional<User> existingUser = repository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }

        String hashedPassword = PasswordUtil.hash(password);

        User user;
        if (type == UserType.FREE) {
            user = new FreeUser(email, hashedPassword, name);
        } else {
            user = new PremiumUser(email, hashedPassword, name);
        }

        repository.save(user);
        return user;
    }
}