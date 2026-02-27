package com.seveneleven.mycontact.main;

import com.seveneleven.mycontact.contact.model.*;
import com.seveneleven.mycontact.contact.service.ContactService;
import com.seveneleven.mycontact.auth.session.SessionManager;

import java.util.*;

public class ContactConsoleHandler {

    private final ContactService contactService;
    private final SessionManager sessionManager;

    public ContactConsoleHandler(ContactService contactService,
                                 SessionManager sessionManager) {
        this.contactService = contactService;
        this.sessionManager = sessionManager;
    }

    public void handleCreateContact(Scanner scanner) {

        if (!sessionManager.isLoggedIn()) {
            System.out.println("Login first.");
            return;
        }

        System.out.print("Contact Name: ");
        String name = scanner.nextLine();

        System.out.println("1. PERSON");
        System.out.println("2. ORGANIZATION");

        int typeChoice = scanner.nextInt();
        scanner.nextLine();

        List<PhoneNumber> phones = new ArrayList<>();
        List<EmailAddress> emails = new ArrayList<>();

        System.out.print("Phone: ");
        phones.add(new PhoneNumber(scanner.nextLine()));

        System.out.print("Email: ");
        emails.add(new EmailAddress(scanner.nextLine()));

        if (typeChoice == 1) {
            contactService.createContact(name, "PERSON", phones, emails);
        } else {
            contactService.createContact(name, "ORGANIZATION", phones, emails);
        }

        System.out.println("Contact created.");
    }

    // UC5 BASIC VIEW
    public void handleViewContacts(Scanner scanner) {

        if (!sessionManager.isLoggedIn()) {
            System.out.println("Login first.");
            return;
        }

        List<Contact> contacts = contactService.getAllContacts();

        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }

        for (int i = 0; i < contacts.size(); i++) {
            System.out.println((i + 1) + ". " + contacts.get(i).getName());
        }

        System.out.print("Select contact: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 1 || index > contacts.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Contact contact = contacts.get(index - 1);

        System.out.println("Name: " + contact.getName());
        System.out.println("Type: " + contact.getType());

        System.out.println("Phones:");
        for (PhoneNumber p : contact.getPhoneNumbers()) {
            System.out.println(" - " + p.getNumber());
        }

        System.out.println("Emails:");
        for (EmailAddress e : contact.getEmailAddresses()) {
            System.out.println(" - " + e.getEmail());
        }
    }
    
    public void handleEditContact(Scanner scanner) {

        if (!sessionManager.isLoggedIn()) {
            System.out.println("Login first.");
            return;
        }

        List<Contact> contacts = contactService.getAllContacts();

        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }

        for (int i = 0; i < contacts.size(); i++) {
            System.out.println((i + 1) + ". " + contacts.get(i).getName());
        }

        System.out.print("Select contact to edit: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 1 || index > contacts.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Contact contact = contacts.get(index - 1);

        System.out.println("1. Update Name");
        System.out.println("2. Add Phone");
        System.out.println("3. Add Email");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {

            case 1:
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                contactService.updateContactName(contact, newName);
                System.out.println("Name updated.");
                break;

            case 2:
                System.out.print("Enter new phone: ");
                String phone = scanner.nextLine();
                contactService.addPhone(contact, phone);
                System.out.println("Phone added.");
                break;

            case 3:
                System.out.print("Enter new email: ");
                String email = scanner.nextLine();
                contactService.addEmail(contact, email);
                System.out.println("Email added.");
                break;

            default:
                System.out.println("Invalid option.");
        }
    }
    
    public void handleDeleteContact(Scanner scanner) {

        if (!sessionManager.isLoggedIn()) {
            System.out.println("Login first.");
            return;
        }

        List<Contact> contacts = contactService.getAllContacts();

        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }

        for (int i = 0; i < contacts.size(); i++) {
            System.out.println((i + 1) + ". " + contacts.get(i).getName());
        }

        System.out.print("Select contact to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 1 || index > contacts.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Contact contact = contacts.get(index - 1);

        contactService.deleteContact(contact);

        System.out.println("Contact permanently deleted.");
    }
    public void handleSearchContact(Scanner scanner) {

        if (!sessionManager.isLoggedIn()) {
            System.out.println("Login first.");
            return;
        }

        System.out.println("Search by:");
        System.out.println("1. Name");
        System.out.println("2. Phone");
        System.out.println("3. Email");

        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();

        List<Contact> results;

        switch (choice) {

            case 1:
                results = contactService.searchByName(keyword);
                break;

            case 2:
                results = contactService.searchByPhone(keyword);
                break;

            case 3:
                results = contactService.searchByEmail(keyword);
                break;

            default:
                System.out.println("Invalid option.");
                return;
        }

        if (results.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }

        System.out.println("\n=== Search Results ===");

        for (Contact contact : results) {

            System.out.println("Name: " + contact.getName());
            System.out.println("Type: " + contact.getType());

            System.out.println("Phones:");
            for (PhoneNumber p : contact.getPhoneNumbers()) {
                System.out.println(" - " + p.getNumber());
            }

            System.out.println("Emails:");
            for (EmailAddress e : contact.getEmailAddresses()) {
                System.out.println(" - " + e.getEmail());
            }

            System.out.println("------------------------");
        }
    }
    public void handleFilterAndSort(Scanner scanner) {

        if (!sessionManager.isLoggedIn()) {
            System.out.println("Login first.");
            return;
        }

        System.out.println("1. Filter by Type");
        System.out.println("2. Sort by Name (ASC)");
        System.out.println("3. Sort by Name (DESC)");
        System.out.println("4. Sort by Created Time");

        int choice = scanner.nextInt();
        scanner.nextLine();

        List<Contact> results;

        switch (choice) {

            case 1:
                System.out.print("Enter type (PERSON / ORGANIZATION): ");
                String type = scanner.nextLine();
                results = contactService.filterByType(type);
                break;

            case 2:
                results = contactService.sortByNameAsc();
                break;

            case 3:
                results = contactService.sortByNameDesc();
                break;

            case 4:
                results = contactService.sortByCreatedTime();
                break;

            default:
                System.out.println("Invalid option.");
                return;
        }

        if (results.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }

        System.out.println("\n=== Results ===");

        for (Contact contact : results) {

            System.out.println("Name: " + contact.getName());
            System.out.println("Type: " + contact.getType());
            System.out.println("Created: " + contact.getCreatedAt());
            System.out.println("---------------------");
        }
    }
}