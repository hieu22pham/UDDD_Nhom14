package com.example.uddd_nhom14.obj;

public class Rent {
    String username, roomnumber;

    public Rent() {}

    public Rent(String username, String roomnumber) {
        this.username = username;
        this.roomnumber = roomnumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }
}
