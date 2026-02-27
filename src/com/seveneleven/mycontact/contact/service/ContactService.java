package com.seveneleven.mycontact.contact.service;

import com.seveneleven.mycontact.contact.model.*;
import com.seveneleven.mycontact.contact.repository.ContactRepository;

import java.util.List;

public class ContactService {

    private final ContactRepository repository;

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    public void createContact(String name,
                              String type,
                              List<PhoneNumber> phones,
                              List<EmailAddress> emails) {

        Contact contact;

        if (type.equalsIgnoreCase("PERSON")) {
            contact = new PersonContact(name, phones, emails);
        } else {
            contact = new OrganizationContact(name, phones, emails);
        }

        repository.save(contact);
    }

    public List<Contact> getAllContacts() {
        return repository.findAll();
    }
}