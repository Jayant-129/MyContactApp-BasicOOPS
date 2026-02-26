package com.seveneleven.mycontact.user.service;

import java.util.Optional;

import com.seveneleven.mycontact.user.model.User;
import com.seveneleven.mycontact.user.model.UserType;
import com.seveneleven.mycontacts.user.repository.UserRepository;
import com.seveneleven.mycontact.user.model.FreeUser;
import com.seveneleven.mycontact.user.model.PremiumUser;


public class UserService {

    private final UserRepository repository;
    private final UserValidatorService validator;

    public UserService(UserRepository repository,
                       UserValidatorService validator) {
        this.repository = repository;
        this.validator = validator;
    }

    // =========================
    // UC1 - Registration
    // =========================
    public User register(String email,
                         String password,
                         String name,
                         UserType type) {

        validator.validateForRegistration(email, password, name);

        if (repository.existsByEmail(email)) {
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

    // =========================
    // UC3 - View User
    // =========================
    public User getUserByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }


    // UC3 - Update Name

    public void updateName(User user, String newName) {

        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        user.updateName(newName);
    }

    // UC3 - Change Password
    public void changePassword(User user,
                               String oldPassword,
                               String newPassword) {

        if (!PasswordUtil.matches(oldPassword, user.getPasswordHash())) {
            throw new IllegalArgumentException("Old password is incorrect");
        }

        if (newPassword == null || newPassword.length() < 6) {
            throw new IllegalArgumentException("New password must be at least 6 characters");
        }

        String newHash = PasswordUtil.hash(newPassword);
        user.changePassword(newHash);
    }

}