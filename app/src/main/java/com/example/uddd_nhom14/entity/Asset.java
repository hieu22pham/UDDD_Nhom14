package com.example.uddd_nhom14.entity;

public class Asset {
    String roomnumber, roomarea;
    int hasac, haswh, haswm;

    public Asset(String roomnumber, String roomarea, int hasac, int haswh, int haswm) {
        this.roomnumber = roomnumber;
        this.roomarea = roomarea;
        this.hasac = hasac;
        this.haswh = haswh;
        this.haswm = haswm;
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

    public int getHasac() {
        return hasac;
    }

    public void setHasac(int hasac) {
        this.hasac = hasac;
    }

    public int getHaswh() {
        return haswh;
    }

    public void setHaswh(int haswh) {
        this.haswh = haswh;
    }

    public int getHaswm() {
        return haswm;
    }

    public void setHaswm(int haswm) {
        this.haswm = haswm;
    }


}
