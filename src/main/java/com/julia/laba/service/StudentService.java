package com.julia.laba.service;

import com.julia.laba.dao.StudentDAO;
import com.julia.laba.data.Student;

import java.util.List;

public class StudentService {
    private final StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public Student createStudent(String name, Long groupId) {
        Student student = new Student();

        student.setGroupId(groupId);
        student.setStudentName(name);
        studentDAO.save(student);
        return student;
    }

    public List<Student> findAllStudents() {
        return studentDAO.findAll();
    }
}
