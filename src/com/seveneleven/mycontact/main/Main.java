package com.seveneleven.mycontact.main;

import com.seveneleven.mycontacts.user.repository.*;
import com.seveneleven.mycontact.user.service.*;
import com.seveneleven.mycontact.auth.session.SessionManager;
import com.seveneleven.mycontact.auth.service.AuthenticationService;
import com.seveneleven.mycontact.auth.provider.*;

import com.seveneleven.mycontact.contact.repository.*;
import com.seveneleven.mycontact.contact.service.*;

import com.seveneleven.mycontact.group.repository.*;
import com.seveneleven.mycontact.group.service.*;

public class Main {

    public static void main(String[] args) {

        // ===== USER SETUP =====
        UserRepository userRepository = new InMemoryUserRepository();
        UserValidatorService validator = new UserValidatorService();
        UserService userService = new UserService(userRepository, validator);

        // ===== AUTH SETUP =====
        SessionManager sessionManager = new SessionManager();
        AuthenticationProvider provider =
                new BasicAuthenticationProvider(userRepository);
        AuthenticationService authService =
                new AuthenticationService(provider, sessionManager);

        // ===== CONTACT SETUP =====
        ContactRepository contactRepository = new InMemoryContactRepository();
        ContactService contactService = new ContactService(contactRepository);

        // ===== GROUP SETUP =====
        GroupRepository groupRepository = new InMemoryGroupRepository();
        GroupService groupService = new GroupService(groupRepository);

        // ===== START APP =====
        ConsoleApp app = new ConsoleApp(
                userService,
                authService,
                sessionManager,
                contactService,
                groupService
        );

        app.start();
    }
}