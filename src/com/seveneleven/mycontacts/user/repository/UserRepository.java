package com.seveneleven.mycontacts.user.repository;

import java.util.Optional;
import com.seveneleven.mycontact.user.model.User;
import java.util.List;

public interface UserRepository {

    void save(User user);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    void deleteByEmail(String email);

    List<User> findAll();
}