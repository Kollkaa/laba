package com.julia.laba.config;

import com.julia.laba.dao.*;
import com.julia.laba.service.*;

import java.util.HashMap;
import java.util.Map;

public class BeanFactory {
    private static Map<Class<?>, Object> beans = new HashMap<>();

    static {
        StudentDAO dao = new StudentDAO();
        StudentService service = new StudentService(dao);
        beans.put(StudentDAO.class, dao);
        beans.put(StudentService.class, service);

        GroupDAO groupDAO = new GroupDAO();
        GroupService groupService = new GroupService(groupDAO);
        beans.put(GroupDAO.class, groupDAO);
        beans.put(GroupService.class, groupService);

        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        beans.put(UserDAO.class, userDAO);
        beans.put(UserService.class, userService);

        SubjectDAO subjectDAO = new SubjectDAO();
        SubjectService subjectService = new SubjectService(subjectDAO);
        beans.put(SubjectDAO.class, dao);
        beans.put(SubjectService.class, subjectService);

        TestDAO testDAO = new TestDAO();
        TestService testService = new TestService(testDAO);
        beans.put(TestDAO.class, testDAO);
        beans.put(TestService.class, testService);
    }

    public static Object getBean(Class<?> beanClass) {
        return beans.get(beanClass);
    }


}
