package com.julia.laba.data;

public class Test {
    private Long id;
    private Long userId;
    private Long subjectId;
    private Long studentId;
    private Long mark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getMark() {
        return mark;
    }

    public void setMark(Long mark) {
        this.mark = mark;
    }
    public static final String DB_NAME = "tests";

    public static class Columns {
        public static final String ID = "id";
        public static final String USER_ID = "user_id";
        public static final String SUBJECT_ID = "subject_id";
        public static final String STUDENT_ID = "student_id";
        public static final String MARK = "mark";

    }
}
