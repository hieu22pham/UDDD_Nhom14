package com.example.uddd_nhom14.entity;

public class Profile {
    String username, sdt, email;

    public Profile(String username, String sdt, String email) {
        this.username = username;
        this.sdt = sdt;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
