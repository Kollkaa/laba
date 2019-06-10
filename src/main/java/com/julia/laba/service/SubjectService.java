package com.julia.laba.service;

import com.julia.laba.dao.SubjectDAO;
import com.julia.laba.data.Subject;

import java.util.List;

public class SubjectService {
    private SubjectDAO dao;

    public SubjectService(SubjectDAO dao) {
        this.dao = dao;
    }

    public void createSubject(String description, String name, Long groupId, Long credits) {
        Subject subject = new Subject();
        subject.setDescription(description);
        subject.setGroup(groupId);
        subject.setName(name);
        subject.setCredits(credits);
        dao.save(subject);
    }

    public List<Subject> findAll() {
        return dao.findAll();
    }
}
