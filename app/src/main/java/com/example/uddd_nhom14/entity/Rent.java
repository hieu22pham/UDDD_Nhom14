package com.example.uddd_nhom14.entity;

public class Rent {
    String username, roomnumber, roomarea;

    public Rent() {}

    public Rent(String username, String roomnumber, String roomarea) {
        this.username = username;
        this.roomnumber = roomnumber;
        this.roomarea = roomarea;
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

    public String getRoomarea() {
        return roomarea;
    }

    public void setRoomarea(String roomarea) {
        this.roomarea = roomarea;
    }
}
