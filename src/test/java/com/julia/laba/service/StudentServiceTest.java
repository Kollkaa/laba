package com.julia.laba.service;

import com.julia.laba.dao.StudentDAO;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StudentServiceTest {
    private StudentDAO dao = mock(StudentDAO.class);
    private StudentService service = new StudentService(dao);

    @Test
    public void groupCanBeSaved() {
        service.createStudent("Test", 1L);
        verify(dao).save(any());
    }
}
