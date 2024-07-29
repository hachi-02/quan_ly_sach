package com.example.duanmau.activity.dao;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.duanmau.activity.database.DbHelper;
import com.example.duanmau.activity.model.ThanhVien;


import java.util.ArrayList;


public class ThanhVienDAO {
    private static DbHelper dbHelper;


    public ThanhVienDAO(Context context) {
        dbHelper = new DbHelper(context);
    }
    public static ArrayList<ThanhVien> getListTV() {
        ArrayList<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try {
            Cursor cursor = database.rawQuery("select * from ThanhVien",null);
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                do {
                    list.add(new ThanhVien(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2)));
                }while (cursor.moveToNext());
                database.setTransactionSuccessful();
            }
        } catch (Exception e){
            Log.e(TAG,"getListTV: "+e);
        } finally {
            database.endTransaction();
        }
        return list;
    }
    public boolean AddThanhVien(ThanhVien thanhVien){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten",thanhVien.getHoten());
        values.put("sdt",thanhVien.getSdt());
        long check = database.insert("ThanhVien",null,values);
        return check != -1;
    }
    public  boolean DeteleThanhVien(int id){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int check = database.delete("ThanhVien","maTV=?",new String[]{String.valueOf(id)});
        return check != -1;
    }

    public  boolean UpdateThanhVien(ThanhVien thanhVien){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten",thanhVien.getHoten());
        values.put("sdt",thanhVien.getSdt());
        int check = database.update("ThanhVien",values,"maTV=?",new String[]{String.valueOf(thanhVien.getMaTV())});
        return check != -1;
    }
}
