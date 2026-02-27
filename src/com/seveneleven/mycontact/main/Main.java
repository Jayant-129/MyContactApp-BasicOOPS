package com.seveneleven.mycontact.main;

import com.seveneleven.mycontact.user.service.*;
import com.seveneleven.mycontacts.user.repository.*;
import com.seveneleven.mycontact.auth.session.SessionManager;
import com.seveneleven.mycontact.auth.service.AuthenticationService;
import com.seveneleven.mycontact.auth.provider.*;
import com.seveneleven.mycontact.contact.repository.*;
import com.seveneleven.mycontact.contact.service.*;

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

        ContactRepository contactRepository = new InMemoryContactRepository();
        ContactService contactService = new ContactService(contactRepository);

        ConsoleApp app = new ConsoleApp(
                userService,
                authService,
                sessionManager,
                contactService
        );

        app.start();
    }
}