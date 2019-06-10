package com.julia.laba.data;

public class Student {
    private Long id;
    private Long groupId;
    private String studentName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


    public static final String DB_NAME = "students";
    public static class Columns {
        public static final String ID = "id";
        public static final String NAME = "student_name";
        public static final String AGENCY_ID = "group_id";
    }
}
