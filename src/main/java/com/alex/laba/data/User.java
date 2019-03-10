package com.alex.laba.data;

public class User {
    private Long id;
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static final String DB_NAME = "users";


    public static class Columns {
        public static final String ID = "id";
        public static final String USER_NAME = "user_name";
    }
}
