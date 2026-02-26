package com.seveneleven.mycontact.main;

import com.seveneleven.mycontact.user.model.UserType;
import com.seveneleven.mycontact.user.service.*;
import com.seveneleven.mycontacts.user.repository.*;
import com.seveneleven.mycontact.auth.session.SessionManager;
import com.seveneleven.mycontact.auth.service.AuthenticationService;
import com.seveneleven.mycontact.auth.provider.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        UserRepository userRepository = new InMemoryUserRepository();

        UserValidatorService validator = new UserValidatorService();
        UserService userService = new UserService(userRepository, validator);

        SessionManager sessionManager = new SessionManager();
        AuthenticationProvider provider =
                new BasicAuthenticationProvider(userRepository);
        AuthenticationService authService =
                new AuthenticationService(provider, sessionManager);

        startConsole(userService, authService, sessionManager);
    }

    private static void startConsole(UserService userService,
                                     AuthenticationService authService,
                                     SessionManager sessionManager) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n=== MyContacts ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Logout");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    handleRegistration(scanner, userService);
                    break;

                case 2:
                    handleLogin(scanner, authService);
                    break;

                case 3:
                    authService.logout();
                    System.out.println("Logged out successfully.");
                    break;

                case 4:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice");
            }

            if (sessionManager.isLoggedIn()) {
                System.out.println("Currently Logged In: "
                        + sessionManager.getCurrentUser().getEmail());
            }
        }
    }

    private static void handleRegistration(Scanner scanner,
                                           UserService userService) {

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.println("Select User Type:");
        System.out.println("1. FREE");
        System.out.println("2. PREMIUM");
        System.out.print("Enter choice: ");

        int typeChoice = scanner.nextInt();
        scanner.nextLine();

        UserType userType;

        if (typeChoice == 1) {
            userType = UserType.FREE;
        } else if (typeChoice == 2) {
            userType = UserType.PREMIUM;
        } else {
            System.out.println("Invalid user type selected.");
            return;
        }

        try {
            userService.register(email, password, name, userType);
            System.out.println("User registered successfully as " + userType + "!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void handleLogin(Scanner scanner,
                                    AuthenticationService authService) {

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        try {
            authService.login(email, password);
            System.out.println("Login successful!");
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }
}