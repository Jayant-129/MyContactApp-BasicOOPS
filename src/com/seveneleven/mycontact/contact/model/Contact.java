package com.seveneleven.mycontact.contact.model;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public abstract class Contact {

    private final UUID id;
    private String name;
    private final List<PhoneNumber> phoneNumbers;
    private final List<EmailAddress> emailAddresses;
    private final LocalDateTime createdAt;

    protected Contact(String name,
                      List<PhoneNumber> phoneNumbers,
                      List<EmailAddress> emailAddresses) {

        this.id = UUID.randomUUID();
        this.name = name;
        this.phoneNumbers = phoneNumbers;
        this.emailAddresses = emailAddresses;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public List<PhoneNumber> getPhoneNumbers() { return phoneNumbers; }
    public List<EmailAddress> getEmailAddresses() { return emailAddresses; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getType();
}