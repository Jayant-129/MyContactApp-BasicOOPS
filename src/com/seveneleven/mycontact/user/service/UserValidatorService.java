package com.seveneleven.mycontact.user.service;


import java.util.regex.Pattern;

public class UserValidatorService {

    public void validateForRegistration(String email, String password, String name) {
        validateEmail(email);
        validatePassword(password);
        validateName(name);
    }

    private void validateEmail(String email) {
        if (email == null ||
            !Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
