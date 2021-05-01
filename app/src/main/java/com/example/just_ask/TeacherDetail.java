package com.example.just_ask;

public class TeacherDetail {
    String name;
    public String class1, subject;

    public TeacherDetail(String name, String class1, String subject) {
        this.name = name;
        this.class1 = class1;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
