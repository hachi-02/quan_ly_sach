package com.example.duanmau.activity.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "QLTV", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String ACCOUNTS = "create table USERS(id integer primary key autoincrement, username text, password text)";
        sqLiteDatabase.execSQL(ACCOUNTS);

        String accounts = "INSERT INTO USERS VALUES (1,'huunhat22','huunhat22')," +
                "(2,'luongnhat22','nhatnhat22')," +
                "(3,'luongnhat2004','nhat2004')," +
                "(4,'nhatluong22','abc1234')";
        sqLiteDatabase.execSQL(accounts);
        String THUTHU = "create table ThuThu(" +
                "maTT text primary key," +
                "hoten text not null," +
                "namSinh text not null)";
        sqLiteDatabase.execSQL(THUTHU);
        String dataTT ="insert into ThuThu values(" +
                "1,'Phan Đỗ Hữu Thắng',2004)," +
                "(2,'Nguyễn Trí Dũng',2005)," +
                "(3,'Trần Trung Hiếu',2005)," +
                "(4,'Trương Đan Trường',2005)," +
                "(5,'Đặng Gia Hy',2005)";
        sqLiteDatabase.execSQL(dataTT);
        String THANHVIEN ="create table ThanhVien(" +
                "maTV integer primary key autoincrement," +
                "hoten text not null, " +
                "sdt text not null)";
        sqLiteDatabase.execSQL(THANHVIEN);

        String dataTV ="insert into ThanhVien values(" +
                "1,'Phan Đỗ Hữu Thắng',0396619631)," +
                "(2,'Nguyễn Trí Dũng',0396619631)," +
                "(3,'Trần Trung Hiếu',0396619631)," +
                "(4,'Trương Đan Trường',0396619631)," +
                "(5,'Đağ Gia Hy',0396619631)";
        sqLiteDatabase.execSQL(dataTV);

        String LOAISACH = "create table LoaiSach(" +
                "maloai integer primary key autoincrement," +
                "tenloai text not null," +
                "soluong integer not null)";
        sqLiteDatabase.execSQL(LOAISACH);
        String dataLS ="insert into LoaiSach values(" +
                "1,'Tình cảm',1000)," +
                "(2,'Hài kịch',2000)," +
                "(3,'Kinh dị',4000)," +
                "(4,'Triết học',2000)," +
                "(5,'Kinh tế',2000)";
        sqLiteDatabase.execSQL(dataLS);
        String SACH = "create table Sach(" +
                "maSach integer primary key autoincrement," +
                "tensach text not null, " +
                "theloai text not null, " +
                "tacgia text not null, " +
                "soluongs integer );";
        sqLiteDatabase.execSQL(SACH);
        String dataS ="insert into Sach values(" +
                "1,'Doraemon','Truyện tranh','Hữu Thắng',100)," +
                "(2,'Bắt trẻ đồng xanh','kinh dị','Stephen King',2000)," +
                "(3,'Thây ma','kinh dị','Stephen King',2000)";
        sqLiteDatabase.execSQL(dataS);

        String PHIEUMUON = "create table PhieuMuon(" +
                "maPM integer primary key autoincrement," +
                "tensachPM text not null, " +
                "tenPM text not null, " +
                "soluongPM integer, " +
                "ngaythue text not null );";
        sqLiteDatabase.execSQL(PHIEUMUON);
        String dataPM ="insert into PhieuMuon values(" +
                "1,'Doraemon','Huu Thắng',01,'22/07/2024')," +
                "(2,'Bắt trẻ đồng xanh','Trí Dũng',02,'25/07/2024')," +
                "(3,'Naruto','Đan Trường',03,'26/07/2024')";
        sqLiteDatabase.execSQL(dataPM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public int login(String ten, String mk) {
        int result=0;
        String str[]= new String[2];
        str[0]=ten;
        str[1]=mk;
        SQLiteDatabase database = getReadableDatabase();
        Cursor c = database.rawQuery("select * from USERS where username=? and password=?",str);
        if (c.moveToNext()){
            result=1;
        }
        return result;
    }
}
