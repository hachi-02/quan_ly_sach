package Model;

public class SanPham {
    public int masp,soluong,dongia;
    public String tentp,tentg;
    public SanPham(int masp,String tentp, String tentg,int soluong, int dongia){
        this.masp=masp;
        this.tentp=tentp;
        this.tentg=tentg;
        this.soluong=soluong;
        this.dongia=dongia;
    }
    public SanPham(String tentp, String tentg,int soluong, int dongia){
        this.tentp=tentp;
        this.tentg=tentg;
        this.soluong=soluong;
        this.dongia=dongia;
    }
}
