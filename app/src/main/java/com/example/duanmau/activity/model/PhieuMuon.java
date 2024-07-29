package com.example.duanmau.activity.model;

public class PhieuMuon {
    int maPM;
    String tensachPM;
    String tenPM;
    int soluongPM;
    String ngaythue;

    public PhieuMuon(int maPM, String tensachPM, String tenPM, int soluongPM, String ngaythue) {
        this.maPM = maPM;
        this.tensachPM = tensachPM;
        this.tenPM = tenPM;
        this.soluongPM = soluongPM;
        this.ngaythue = ngaythue;
    }
    public PhieuMuon(String tensachPM, String tenPM, int soluongPM, String ngaythue) {

        this.tensachPM = tensachPM;
        this.tenPM = tenPM;
        this.soluongPM = soluongPM;
        this.ngaythue= ngaythue;

    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public String getTensachPM() {
        return tensachPM;
    }

    public void setTensachPM(String tensachPM) {
        this.tensachPM = tensachPM;
    }

    public String getTenPM() {
        return tenPM;
    }

    public void setTenPM(String tenPM) {
        this.tenPM = tenPM;
    }

    public int getSoluongPM() {
        return soluongPM;
    }

    public void setSoluongPM(int soluongPM) {
        this.soluongPM = soluongPM;
    }

    public String getNgaythue() {
        return ngaythue;
    }

    public void setNgaythue(String ngaythue) {
        this.ngaythue = ngaythue;
    }
}

