package com.example.duanmau.activity.dao;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.activity.database.DbHelper;
import com.example.duanmau.activity.model.LoaiSach;


import java.util.ArrayList;

public class LoaiSachDAO {
    private DbHelper dbHelper;


    public LoaiSachDAO(Context context) {
        dbHelper = new DbHelper(context);
    }
    public ArrayList<LoaiSach> getListLS() {
        ArrayList<LoaiSach> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try {
            Cursor cursor = database.rawQuery("select * from LoaiSach",null);
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                do {
                    list.add(new LoaiSach(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2)));
                }while (cursor.moveToNext());
                database.setTransactionSuccessful();
            }
        } catch (Exception e){
            Log.e(TAG,"getListLS: "+e);
        } finally {
            database.endTransaction();
        }
        return list;
    }
    public boolean AddLoaiSach(LoaiSach loaiSach){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenloai",loaiSach.getTenloai());
        values.put("soluong",loaiSach.getSoluong());

        long check = database.insert("LoaiSach",null,values);
        return check != -1;
    }
    public  boolean DeteleSanPham(int id){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int check = database.delete("LoaiSach","maloai=?",new String[]{String.valueOf(id)});
        return check != -1;
    }

    public  boolean UpdateSapPham(LoaiSach loaiSach){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenloai",loaiSach.getTenloai());
        int check = database.update("LoaiSach",values,"maloai=?",new String[]{String.valueOf(loaiSach.getMaLoai())});
        return check != -1;
    }
}
