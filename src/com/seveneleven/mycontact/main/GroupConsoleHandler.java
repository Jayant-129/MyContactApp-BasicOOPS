package com.seveneleven.mycontact.main;

import com.seveneleven.mycontact.group.model.Group;
import com.seveneleven.mycontact.group.service.GroupService;
import com.seveneleven.mycontact.contact.model.Contact;
import com.seveneleven.mycontact.contact.service.ContactService;
import com.seveneleven.mycontact.auth.session.SessionManager;

import java.util.List;
import java.util.Scanner;

public class GroupConsoleHandler {

    private final GroupService groupService;
    private final ContactService contactService;
    private final SessionManager sessionManager;

    public GroupConsoleHandler(GroupService groupService,
                               ContactService contactService,
                               SessionManager sessionManager) {
        this.groupService = groupService;
        this.contactService = contactService;
        this.sessionManager = sessionManager;
    }

    public void handleCreateGroup(Scanner scanner) {

        if (!sessionManager.isLoggedIn()) {
            System.out.println("Login first.");
            return;
        }

        System.out.print("Enter Group Name: ");
        String name = scanner.nextLine();

        groupService.createGroup(name);
        System.out.println("Group created.");
    }

    public void handleAddContactToGroup(Scanner scanner) {

        List<Group> groups = groupService.getAllGroups();
        List<Contact> contacts = contactService.getAllContacts();

        if (groups.isEmpty() || contacts.isEmpty()) {
            System.out.println("Need at least one group and one contact.");
            return;
        }

        for (int i = 0; i < groups.size(); i++) {
            System.out.println((i + 1) + ". " + groups.get(i).getName());
        }

        System.out.print("Select Group: ");
        int groupIndex = scanner.nextInt();
        scanner.nextLine();

        Group group = groups.get(groupIndex - 1);

        for (int i = 0; i < contacts.size(); i++) {
            System.out.println((i + 1) + ". " + contacts.get(i).getName());
        }

        System.out.print("Select Contact: ");
        int contactIndex = scanner.nextInt();
        scanner.nextLine();

        Contact contact = contacts.get(contactIndex - 1);

        groupService.addContactToGroup(group, contact);
        System.out.println("Contact added to group.");
    }

    public void handleViewGroups() {

        List<Group> groups = groupService.getAllGroups();

        if (groups.isEmpty()) {
            System.out.println("No groups found.");
            return;
        }

        for (Group g : groups) {
            System.out.println("\nGroup: " + g.getName());
            for (Contact c : g.getContacts()) {
                System.out.println(" - " + c.getName());
            }
        }
    }
}