package com.seveneleven.mycontact.user.model;

public class FreeUser extends User {

    private static final int CONTACT_LIMIT = 100;

    public FreeUser(String email, String passwordHash, String name) {
        super(email, passwordHash, name);
    }

    @Override
    public UserType getUserType() {
        return UserType.FREE;
    }

    public int getContactLimit() {
        return CONTACT_LIMIT;
    }
}