package com.seveneleven.mycontact.main;


import com.seveneleven.mycontact.user.model.UserType;
import com.seveneleven.mycontact.user.model.User;
import com.seveneleven.mycontact.user.service.UserService;
import com.seveneleven.mycontact.auth.service.AuthenticationService;
import com.seveneleven.mycontact.auth.session.SessionManager;

import java.util.Scanner;

public class UserConsoleHandler {

    private final UserService userService;

    public UserConsoleHandler(UserService userService) {
        this.userService = userService;
    }

    public void handleRegistration(Scanner scanner) {

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.println("1. FREE");
        System.out.println("2. PREMIUM");

        int typeChoice = scanner.nextInt();
        scanner.nextLine();

        UserType type = (typeChoice == 1)
                ? UserType.FREE
                : UserType.PREMIUM;

        userService.register(email, password, name, type);
        System.out.println("User registered successfully.");
    }

    public void handleLogin(Scanner scanner,
                            AuthenticationService authService) {

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        authService.login(email, password);
        System.out.println("Login successful.");
    }

    public void handleProfile(Scanner scanner,
                              SessionManager sessionManager) {

        if (!sessionManager.isLoggedIn()) {
            System.out.println("Login first.");
            return;
        }

        User user = sessionManager.getCurrentUser();

        System.out.println("Email: " + user.getEmail());
        System.out.println("Name: " + user.getName());
        System.out.println("Type: " + user.getUserType());
    }
}