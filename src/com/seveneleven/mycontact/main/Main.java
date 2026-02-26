package com.seveneleven.mycontact.main;

import com.seveneleven.mycontact.user.model.UserType;
import com.seveneleven.mycontact.user.model.User;
import com.seveneleven.mycontact.user.service.UserService;
import com.seveneleven.mycontact.user.service.UserValidatorService;
import com.seveneleven.mycontacts.user.repository.*;
import com.seveneleven.mycontact.auth.session.SessionManager;
import com.seveneleven.mycontact.auth.service.AuthenticationService;
import com.seveneleven.mycontact.auth.provider.AuthenticationProvider;
import com.seveneleven.mycontact.auth.provider.BasicAuthenticationProvider;

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
            System.out.println("3. Profile");
            System.out.println("4. Logout");
            System.out.println("5. Exit");
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
                    handleProfile(scanner, userService, sessionManager);
                    break;

                case 4:
                    authService.logout();
                    System.out.println("Logged out successfully.");
                    break;

                case 5:
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

    private static void handleProfile(Scanner scanner,
                                      UserService userService,
                                      SessionManager sessionManager) {

        if (!sessionManager.isLoggedIn()) {
            System.out.println("You must login first.");
            return;
        }

        User currentUser = sessionManager.getCurrentUser();

        System.out.println("\n=== Profile Menu ===");
        System.out.println("1. View Profile");
        System.out.println("2. Update Name");
        System.out.println("3. Change Password");
        System.out.print("Choose option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        try {

            switch (choice) {

                case 1:
                    System.out.println("Email: " + currentUser.getEmail());
                    System.out.println("Name: " + currentUser.getName());
                    System.out.println("Type: " + currentUser.getUserType());
                    break;

                case 2:
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    userService.updateName(currentUser, newName);
                    System.out.println("Name updated successfully.");
                    break;

                case 3:
                    System.out.print("Enter old password: ");
                    String oldPassword = scanner.nextLine();

                    System.out.print("Enter new password: ");
                    String newPassword = scanner.nextLine();

                    userService.changePassword(currentUser, oldPassword, newPassword);
                    System.out.println("Password changed successfully.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}