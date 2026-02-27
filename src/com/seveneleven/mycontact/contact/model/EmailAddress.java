package com.seveneleven.mycontact.contact.model;

import java.util.regex.Pattern;

public class EmailAddress {

    private final String email;
    
    private void validateEmail(String email) {
        if (email == null ||
            !Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    public EmailAddress(String email) {
    	try {
    		validateEmail(email);
    	}
    	catch( IllegalArgumentException e) {
    		System.out.println(e.getMessage());
    	}
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}