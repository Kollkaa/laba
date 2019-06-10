package com.julia.laba.service;

import com.julia.laba.dao.UserDAO;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    private UserDAO dao = mock(UserDAO.class);
    private UserService service = new UserService(dao);

    @Test
    public void userCanBeSaved() {
        service.createUser("Test", "PAssw");
        verify(dao).save(any());
    }
}
