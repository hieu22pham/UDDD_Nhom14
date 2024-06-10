package com.example.uddd_nhom14.entity;

public class Request {
    String username, roomnumber, area;
    int requesttype;

    public Request(String username, String roomnumber, String area, int requesttype) {
        this.username = username;
        this.roomnumber = roomnumber;
        this.area = area;
        this.requesttype = requesttype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRequesttype() {
        return requesttype;
    }

    public void setRequesttype(int requesttype) {
        this.requesttype = requesttype;
    }

    public String getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
