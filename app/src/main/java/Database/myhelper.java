package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class myhelper extends SQLiteOpenHelper {

    public myhelper(@Nullable Context context){
        super(context,"Quan_Ly_Sach", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //người dùng
        String sql1= "create table user " +
                "( " +
                "username text primary key, " +
                "password text " +
                ")";
        db.execSQL(sql1);

        //sản phẩm
        String sql="create table sanpham " +
                "( " +
                "masp integer primary key autoincrement, " +
                "tentp text, " +
                "theloai text, "+
                "soluong integer," +
                "dongia integer " +
                ")";
        db.execSQL(sql);
        String LOAISACH = "create table LoaiSach(" +
                "TenTP integer primary key autoincrement," +
                "theloai text not null," +
                "soluong integer not null)"+
                "dongia integer not null";
        db.execSQL(LOAISACH);
        String dataLS ="insert into LoaiSach values(" +
                "1,'Tình cảm',1000,10.000)," +
                "(2,'Hài kịch',2000,30.000)," +
                "(3,'Kinh dị',4000,20.0000)," +
                "(4,'Triết học',2000,11.000)," +
                "(5,'Kinh tế',2000,40.000)";
        db.execSQL(dataLS);
        String SACH = "create table Sach(" +
                "maSach integer primary key autoincrement," +
                "TenTP text not null, " +
                "theloai text not null, " +
                "gia text not null, " +
                "soluongs integer );";
        db.execSQL(SACH);
        String dataS ="insert into Sach values(" +
                "1,'Doraemon','Truyện tranh','Đan Trường',100)," +
                "(2,'Bắt trẻ đồng xanh','kinh dị','Stephen King',2000)," +
                "(3,'Thây ma','kinh dị','Stephen King',2000)";
        db.execSQL(dataS);

        String PHIEUMUON = "create table PhieuMuon(" +
                "maPM integer primary key autoincrement," +
                "tensachPM text not null, " +
                "tenPM text not null, " +
                "soluongPM integer, " +
                "ngaymuon text not null );";
        db.execSQL(PHIEUMUON);
        String dataPM ="insert into PhieuMuon values(" +
                "1,'Doraemon','aaaaaaaa',01,'22/09/2024')," +
                "(2,'Conan','ccccccccc',02,'10/10/2024')," +
                "(3,'Naruto','bbbbbbbb',03,'10/10/2024')";
        db.execSQL(dataPM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("myhelper", "onUpgrade() called");
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists sanpham");
        onCreate(db);
    }
}
