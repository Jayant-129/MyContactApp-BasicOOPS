package com.seveneleven.mycontact.user.model;

import java.util.UUID;

public abstract class User {

    private final UUID id;
    private final String email;
    private String passwordHash;
    private String name;

    protected User(String email, String passwordHash, String name) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.passwordHash = passwordHash;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getName() {
        return name;
    }

    public void changePassword(String newHash) {
        this.passwordHash = newHash;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public abstract UserType getUserType();
}