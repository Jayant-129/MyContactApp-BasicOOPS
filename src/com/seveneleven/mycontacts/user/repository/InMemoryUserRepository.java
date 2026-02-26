package com.seveneleven.mycontacts.user.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import com.seveneleven.mycontact.user.model.User;

public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> users = new HashMap<>();
    
    @Override
    public void save(User user) {
        users.put(user.getEmail(), user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(users.get(email));
    }

    @Override
    public boolean existsByEmail(String email) {
        return users.containsKey(email);
    }

    @Override
    public void deleteByEmail(String email) {
        users.remove(email);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}