package com.seveneleven.mycontact.group.model;

import com.seveneleven.mycontact.contact.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Group {

    private final UUID id;
    private String name;
    private final List<Contact> contacts;

    public Group(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.contacts = new ArrayList<>();
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public List<Contact> getContacts() { return contacts; }

    public void setName(String name) {
        this.name = name;
    }
}