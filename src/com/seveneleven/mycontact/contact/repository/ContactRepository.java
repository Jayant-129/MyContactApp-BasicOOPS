package com.seveneleven.mycontact.contact.repository;

import com.seveneleven.mycontact.contact.model.Contact;

import java.util.List;
import java.util.UUID;

public interface ContactRepository {

    void save(Contact contact);

    List<Contact> findAll();

    Contact findById(UUID id);

    void delete(UUID id);
}