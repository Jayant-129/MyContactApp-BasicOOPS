package com.seveneleven.mycontact.user.model;


public class PremiumUser extends User {

    private static final int CONTACT_LIMIT = Integer.MAX_VALUE;

    public PremiumUser(String email, String passwordHash, String name) {
        super(email, passwordHash, name);
    }

    @Override
    public UserType getUserType() {
        return UserType.PREMIUM;
    }

    public int getContactLimit() {
        return CONTACT_LIMIT;
    }
}