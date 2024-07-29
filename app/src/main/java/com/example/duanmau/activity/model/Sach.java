package com.example.duanmau.activity.model;

public class Sach {
    int maSach;
    String tensach;
    String theloai;
    String tacgia;
    int soluongs;

    public Sach(int maSach, String tensach, String theloai, String tacgia, int soluong) {
        this.maSach = maSach;
        this.tensach = tensach;
        this.theloai = theloai;
        this.tacgia = tacgia;
        this.soluongs = soluong;
    }

    public Sach(String tensach, String theloai, String tacGia, int soluongs) {
//        this.maSach = ;
        this.tensach = tensach;
        this.theloai = theloai;
        this.tacgia = tacGia;
        this.soluongs = soluongs;

    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public int getSoluongs() {
        return soluongs;
    }

    public void setSoluongs(int soluongs) {
        this.soluongs = soluongs;
    }
}