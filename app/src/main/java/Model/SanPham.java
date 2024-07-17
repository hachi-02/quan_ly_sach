package Model;

public class SanPham {
    public int masp,soluong,dongia;
    public String tentp,theloai;
    public SanPham(int masp,String tentp, String theloai,int soluong, int dongia){
        this.masp=masp;
        this.tentp=tentp;
        this.theloai=theloai;
        this.soluong=soluong;
        this.dongia=dongia;
    }
    public SanPham(String tentp, String theloai,int soluong, int dongia){
        this.tentp=tentp;
        this.theloai=theloai;
        this.soluong=soluong;
        this.dongia=dongia;
    }
}
