package com.seveneleven.mycontact.group.repository;

import com.seveneleven.mycontact.group.model.Group;

import java.util.List;
import java.util.UUID;

public interface GroupRepository {

    void save(Group group);

    List<Group> findAll();

    Group findById(UUID id);

    void delete(UUID id);
}