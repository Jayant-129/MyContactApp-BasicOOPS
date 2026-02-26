package com.seveneleven.mycontact.main;

import com.seveneleven.mycontact.user.model.UserType;
import com.seveneleven.mycontact.user.service.UserService;
import com.seveneleven.mycontact.user.service.UserValidatorService;
import com.seveneleven.mycontacts.user.repository.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        UserRepository userRepository = new InMemoryUserRepository();
        UserValidatorService validator = new UserValidatorService();
        UserService userService = new UserService(userRepository, validator);

        startConsole(userService);
    }

    private static void startConsole(UserService userService) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n=== MyContacts ===");
            System.out.println("1. Register");
            System.out.println("2. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                handleRegistration(scanner, userService);
            } 
            else if (choice == 2) {
                System.out.println("Goodbye!");
                break;
            } 
            else {
                System.out.println("Invalid choice");
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
        } 
        else if (typeChoice == 2) {
            userType = UserType.PREMIUM;
        } 
        else {
            System.out.println("Invalid user type selected.");
            return;
        }

        try {
            userService.register(email, password, name, userType);
            System.out.println("User registered successfully as " + userType + "!");
        } 
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}