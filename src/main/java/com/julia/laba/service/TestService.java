package com.julia.laba.service;

import com.julia.laba.dao.TestDAO;
import com.julia.laba.data.Test;

import java.util.List;


public class TestService {
    private final TestDAO dao;

    public TestService(TestDAO dao) {
        this.dao = dao;
    }

    public void createTest(Long userId, Long studentId, Long subjectId, Long mark) {
        Test test = new Test();
        test.setMark(mark);
        test.setUserId(userId);
        test.setStudentId(studentId);
        test.setSubjectId(subjectId);
        dao.save(test);
    }

    public List<Test> findAll() {
        return dao.findAll();
    }
}
