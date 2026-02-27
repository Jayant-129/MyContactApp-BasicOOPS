package com.seveneleven.mycontact.main;

import com.seveneleven.mycontact.user.service.UserService;
import com.seveneleven.mycontact.auth.service.AuthenticationService;
import com.seveneleven.mycontact.auth.session.SessionManager;
import com.seveneleven.mycontact.contact.service.ContactService;
import com.seveneleven.mycontact.group.service.GroupService;

import java.util.Scanner;

public class ConsoleApp {

    private final UserConsoleHandler userHandler;
    private final ContactConsoleHandler contactHandler;
    private final GroupConsoleHandler groupHandler;
    private final AuthenticationService authService;
    private final SessionManager sessionManager;

    public ConsoleApp(UserService userService,
                      AuthenticationService authService,
                      SessionManager sessionManager,
                      ContactService contactService,
                      GroupService groupService) {

        this.userHandler = new UserConsoleHandler(userService);
        this.contactHandler = new ContactConsoleHandler(contactService, sessionManager);
        this.groupHandler = new GroupConsoleHandler(groupService, contactService, sessionManager);

        this.authService = authService;
        this.sessionManager = sessionManager;
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n========= MyContacts =========");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Profile");

            System.out.println("----- Contacts -----");
            System.out.println("4. Create Contact");
            System.out.println("5. View Contacts");
            System.out.println("6. Edit Contact");
            System.out.println("7. Delete Contact");

            System.out.println("----- Groups -----");
            System.out.println("8. Create Group");
            System.out.println("9. Add Contact to Group");
            System.out.println("10. View Groups");
            System.out.println("11. Search Contact");
            
            System.out.println("12. Logout");
            System.out.println("13. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    userHandler.handleRegistration(scanner);
                    break;

                case 2:
                    userHandler.handleLogin(scanner, authService);
                    break;

                case 3:
                    userHandler.handleProfile(scanner, sessionManager);
                    break;

                case 4:
                    contactHandler.handleCreateContact(scanner);
                    break;

                case 5:
                    contactHandler.handleViewContacts(scanner);
                    break;

                case 6:
                    contactHandler.handleEditContact(scanner);
                    break;

                case 7:
                    contactHandler.handleDeleteContact(scanner);
                    break;

                case 8:
                    groupHandler.handleCreateGroup(scanner);
                    break;

                case 9:
                    groupHandler.handleAddContactToGroup(scanner);
                    break;

                case 10:
                    groupHandler.handleViewGroups();
                    break;
                    
                case 11:
                    contactHandler.handleSearchContact(scanner);
                    break;

                case 12:
                    authService.logout();
                    System.out.println("Logged out successfully.");
                    break;

                case 13:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option.");
            }

            if (sessionManager.isLoggedIn()) {
                System.out.println("Logged in as: "
                        + sessionManager.getCurrentUser().getEmail());
            }
        }
    }
}