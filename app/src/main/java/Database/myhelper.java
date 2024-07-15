package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class myhelper extends SQLiteOpenHelper {

    public myhelper(@Nullable Context context){
        super(context,"Quan_Ly_Sach", null, 2);
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
                "tentg text, "+
                "soluong integer," +
                "dongia integer " +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("myhelper", "onUpgrade() called");
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists sanpham");
        onCreate(db);
    }
}
