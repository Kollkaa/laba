package com.julia.laba.data;

public class Subject {
    private Long id;
    private Long group;
    private String name;
    private String description;
    private Long credits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroup() {
        return group;
    }

    public void setGroup(Long group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCredits() {
        return credits;
    }

    public void setCredits(Long credits) {
        this.credits = credits;
    }

    public static final String DB_NAME = "subjects";

    public static class Columns {
        public static final String ID = "id";
        public static final String GROUP_ID = "st_group";
        public static final String NAME = "subject_name";
        public static final String DESCRIPTION = "subject_description";
        public static final String CREDITS = "credits";

    }
}
