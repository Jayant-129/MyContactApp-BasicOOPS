package com.seveneleven.mycontact.group.service;

import com.seveneleven.mycontact.group.model.Group;
import com.seveneleven.mycontact.group.repository.GroupRepository;
import com.seveneleven.mycontact.contact.model.Contact;

import java.util.List;

public class GroupService {

    private final GroupRepository repository;

    public GroupService(GroupRepository repository) {
        this.repository = repository;
    }

    public void createGroup(String name) {
        Group group = new Group(name);
        repository.save(group);
    }

    public List<Group> getAllGroups() {
        return repository.findAll();
    }

    public void addContactToGroup(Group group, Contact contact) {
        group.getContacts().add(contact);
    }
}