package com.example.duanmau.activity.model;

public class LoaiSach {
    int maLoai;
    String tenloai, soluong;


    public LoaiSach(int maLoai, String tenloai, String soluong) {
        this.maLoai = maLoai;
        this.tenloai = tenloai;
        this.soluong = soluong;
    }

    public LoaiSach(String tenloai, String soluong) {
        this.tenloai = tenloai;
        this.soluong = soluong;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }
}

