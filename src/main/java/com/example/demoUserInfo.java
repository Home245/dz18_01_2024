package com.example.demo;

public class UserInfo {
    private int age;
    private String username;
    private long id;

    public UserInfo(String username, long id, int age) {
        this.age = age;
        this.username = username;
        this.id=id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

}
