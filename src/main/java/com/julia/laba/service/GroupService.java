package com.julia.laba.service;

import com.julia.laba.dao.GroupDAO;
import com.julia.laba.data.Group;

import java.util.List;

public class GroupService {
    private GroupDAO dao;

    public GroupService(GroupDAO dao) {
        this.dao = dao;
    }

    public Group createGroup(String name) {
        Group group = new Group();
        group.setGroupName(name);
        dao.save(group);
        return group;
    }

    public List<Group> findAllgroups() {
        return dao.findAll();
    }
}
