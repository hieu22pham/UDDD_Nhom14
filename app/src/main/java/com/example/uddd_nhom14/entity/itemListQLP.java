package com.example.uddd_nhom14.entity;

public class itemListQLP {
    private String stt;
    private String maPhieu;
    private String tenPhieu;
    private String trangThai;

    public itemListQLP(String stt, String maPhieu, String tenPhieu, String trangThai) {
        this.stt = stt;
        this.maPhieu = maPhieu;
        this.tenPhieu = tenPhieu;
        this.trangThai = trangThai;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public String getStt() {
        return stt;
    }

    public String getTenPhieu() {
        return tenPhieu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public void setTenPhieu(String tenPhieu) {
        this.tenPhieu = tenPhieu;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
