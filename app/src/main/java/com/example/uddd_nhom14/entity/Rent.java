package com.example.uddd_nhom14.entity;

public class Rent {
    String username, roomnumber, roomarea, enddate;

    public Rent(String a, String s, String string, String number, String s1) {
    }

    public Rent(String username, String roomnumber, String roomarea, String enddate) {
        this.username = username;
        this.roomnumber = roomnumber;
        this.roomarea = roomarea;
        this.enddate = enddate;
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

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }




}
