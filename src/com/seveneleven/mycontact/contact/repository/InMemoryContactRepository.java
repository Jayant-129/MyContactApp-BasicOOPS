package com.seveneleven.mycontact.contact.repository;


import com.seveneleven.mycontact.contact.model.Contact;

import java.util.*;

public class InMemoryContactRepository implements ContactRepository {

    private final Map<UUID, Contact> contacts = new HashMap<>();

    @Override
    public void save(Contact contact) {
        contacts.put(contact.getId(), contact);
    }

    @Override
    public List<Contact> findAll() {
        return new ArrayList<>(contacts.values());
    }

    @Override
    public Contact findById(UUID id) {
        return contacts.get(id);
    }

    @Override
    public void delete(UUID id) {
        contacts.remove(id);
    }
}