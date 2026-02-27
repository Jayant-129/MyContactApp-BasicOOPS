package com.seveneleven.mycontact.main;

import com.seveneleven.mycontact.user.service.UserService;
import com.seveneleven.mycontact.auth.service.AuthenticationService;
import com.seveneleven.mycontact.auth.session.SessionManager;
import com.seveneleven.mycontact.contact.service.ContactService;

import java.util.Scanner;

public class ConsoleApp {

    private final UserConsoleHandler userHandler;
    private final ContactConsoleHandler contactHandler;
    private final AuthenticationService authService;
    private final SessionManager sessionManager;

    public ConsoleApp(UserService userService,
                      AuthenticationService authService,
                      SessionManager sessionManager,
                      ContactService contactService) {

        this.userHandler = new UserConsoleHandler(userService);
        this.contactHandler = new ContactConsoleHandler(contactService, sessionManager);
        this.authService = authService;
        this.sessionManager = sessionManager;
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n=== MyContacts ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Profile");
            System.out.println("4. Create Contact");
            System.out.println("5. View Contacts");
            System.out.println("6. Logout");
            System.out.println("7. Exit");

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
                    authService.logout();
                    System.out.println("Logged out successfully.");
                    break;

                case 7:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }

            if (sessionManager.isLoggedIn()) {
                System.out.println("Logged in as: "
                        + sessionManager.getCurrentUser().getEmail());
            }
        }
    }
}