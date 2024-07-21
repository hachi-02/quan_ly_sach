package Model;

import java.time.LocalDate;

public class PhieuMuon {
    int mapm,masp;
    String tentp,ten_ngm;
    int sdt;
    LocalDate ngaymuon,ngaytra;
    Boolean trangthai;
    public PhieuMuon(int mapm, String te_ngm,
                              int sdt, String tentp, int masp,
                              LocalDate ngaymuon,LocalDate ngaytra, Boolean trangthai ){
        this.mapm=mapm;
        this.masp=masp;
        this.ten_ngm=te_ngm;
        this.sdt=sdt;
        this.tentp=tentp;
        this.ngaymuon=ngaymuon;
        this.ngaytra=ngaytra;
    }
    public PhieuMuon(String te_ngm,
                     int sdt, String tentp,
                     LocalDate ngaymuon,Boolean trangthai ){
        this.ten_ngm=te_ngm;
        this.sdt=sdt;
        this.tentp=tentp;
        this.ngaymuon=ngaymuon;
        this.trangthai=trangthai;
    }

}
