package com.seveneleven.mycontact.group.repository;

import com.seveneleven.mycontact.group.model.Group;

import java.util.*;

public class InMemoryGroupRepository implements GroupRepository {

    private final Map<UUID, Group> groups = new HashMap<>();

    @Override
    public void save(Group group) {
        groups.put(group.getId(), group);
    }

    @Override
    public List<Group> findAll() {
        return new ArrayList<>(groups.values());
    }

    @Override
    public Group findById(UUID id) {
        return groups.get(id);
    }

    @Override
    public void delete(UUID id) {
        groups.remove(id);
    }
}