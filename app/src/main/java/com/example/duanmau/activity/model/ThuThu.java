package com.example.duanmau.activity.model;

public class ThuThu {
    int maTT;
    String tenTT;
    String namSinh;

    public ThuThu(int maTT, String hoten, String namSinh) {
        this.maTT = maTT;
        this.tenTT = hoten;
        this.namSinh = namSinh;
    }

    public ThuThu(String tenTT, String namSinh) {
        this.tenTT = tenTT;
        this.namSinh = namSinh;
    }

    public int getMaTT() {
        return maTT;
    }

    public void setMaTT(int maTT) {
        this.maTT = maTT;
    }

    public String getTenTT() {
        return tenTT;
    }

    public void setTenTT(String hoten) {
        this.tenTT = hoten;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }
}
