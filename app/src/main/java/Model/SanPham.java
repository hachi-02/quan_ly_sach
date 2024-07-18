package Model;

public class SanPham {
    private int masp,soluong,dongia;
    private String tentp,theloai;
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

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public String getTentp() {
        return tentp;
    }

    public void setTentp(String tentp) {
        this.tentp = tentp;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }


    public SanPham(int masp, int soluong, int dongia, String tentp, String theloai) {
        this.masp = masp;
        this.soluong = soluong;
        this.dongia = dongia;
        this.tentp = tentp;
        this.theloai = theloai;
    }

}
