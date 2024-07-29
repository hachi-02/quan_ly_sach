package com.example.duanmau.activity.model;

public class ThanhVien {
    int maTV;
    String hoten;
    String sdt;

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public ThanhVien(int maTV, String hoten, String sdt) {
        this.maTV = maTV;
        this.hoten = hoten;
        this.sdt = sdt;

    }

    public ThanhVien(String hoten, String sdt) {
        this.hoten = hoten;
        this.sdt = sdt;
    }
}
