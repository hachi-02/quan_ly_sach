package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class myhelper extends SQLiteOpenHelper {

    public myhelper(@Nullable Context context){
        super(context,"Quan_Ly_Sach", null, 5);
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
                "theloai_id text, "+
                "soluong integer," +
                "dongia integer, " +
                "FOREIGN KEY(theloai_id) REFERENCES theloai(id)" +
                ")";
        db.execSQL(sql);
        //thể loại
        String createTableTheLoai = "CREATE TABLE theloai (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ten_theloai TEXT)";
        db.execSQL(createTableTheLoai);
        //phiếu mượn
        String phieuMuon = "create table phieumuon (" +
                "id_phieumuon INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tentp text, " +
                "sdt text, " +
                "tennguoimuon text, " +
                "ngaymuon date, " +
                "ngaytra date, " +
                "masp int, " +
                "FOREIGN KEY(masp) REFERENCES sanpham(masp))";
        db.execSQL(phieuMuon);

        String insertTheLoaiData = "INSERT INTO theloai (ten_theloai) VALUES " +
                "('Hài hước'), " +
                "('Manga'), " +
                "('Khoa học')";
        db.execSQL(insertTheLoaiData);

        String insertSanPhamData = "INSERT INTO sanpham (tentp, theloai_id, soluong, dongia) VALUES " +
                "('Truyện gì đó', 1, 10, 10000), " +
                "('Truyện gì đó 01', 1, 10, 10000)";
        db.execSQL(insertSanPhamData);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("myhelper", "onUpgrade() called");
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists sanpham");
        db.execSQL("DROP TABLE IF EXISTS theloai");
        db.execSQL("drop table if exists phieumuon");
        onCreate(db);
    }
}
