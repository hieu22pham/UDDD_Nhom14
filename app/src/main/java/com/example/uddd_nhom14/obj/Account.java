package com.example.uddd_nhom14.obj;

public class Account {
    String username;
    String password;
    String name;
    int role;

    public Account() {}

    public Account(String username, String password, String name, int role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public Account(String username, String password, int role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}