package com.seveneleven.mycontact.contact.service;

import com.seveneleven.mycontact.contact.model.*;
import com.seveneleven.mycontact.contact.repository.ContactRepository;

import java.util.ArrayList;
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
    
    public void updateContactName(Contact contact, String newName) {
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        contact.setName(newName);
    }

    public void addPhone(Contact contact, String phone) {
        contact.getPhoneNumbers().add(new PhoneNumber(phone));
    }

    public void addEmail(Contact contact, String email) {
        contact.getEmailAddresses().add(new EmailAddress(email));
    }

    public List<Contact> getAllContacts() {
        return repository.findAll();
    }
    
    public void deleteContact(Contact contact) {
        repository.delete(contact.getId());
    }
    public List<Contact> searchByName(String keyword) {

        List<Contact> result = new ArrayList<>();

        for (Contact contact : repository.findAll()) {
            if (contact.getName().toLowerCase()
                    .contains(keyword.toLowerCase())) {
                result.add(contact);
            }
        }

        return result;
    }
    public List<Contact> searchByPhone(String keyword) {

        List<Contact> result = new ArrayList<>();

        for (Contact contact : repository.findAll()) {

            for (PhoneNumber phone : contact.getPhoneNumbers()) {
                if (phone.getNumber().contains(keyword)) {
                    result.add(contact);
                    break;
                }
            }
        }

        return result;
    }
    public List<Contact> searchByEmail(String keyword) {

        List<Contact> result = new ArrayList<>();

        for (Contact contact : repository.findAll()) {

            for (EmailAddress email : contact.getEmailAddresses()) {
                if (email.getEmail().toLowerCase()
                        .contains(keyword.toLowerCase())) {
                    result.add(contact);
                    break;
                }
            }
        }

        return result;
    }
}