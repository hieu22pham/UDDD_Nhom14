package com.example.uddd_nhom14.obj;

public class Room {
    String roomnumber, area, floor;

    public Room() {}

    public Room(String roomnumber, String area, String floor) {
        this.roomnumber = roomnumber;
        this.area = area;
        this.floor = floor;
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

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }
}
