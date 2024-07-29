package com.example.duanmau.activity.dao;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.activity.database.DbHelper;
import com.example.duanmau.activity.model.Sach;


import java.util.ArrayList;

public class SachDAO {
    private static DbHelper dbHelper;


    public SachDAO(Context context) {
        dbHelper = new DbHelper(context);
    }
    public static ArrayList<Sach> getListS() {
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try {
            Cursor cursor = database.rawQuery("select * from Sach",null);
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                do {
                    list.add(new Sach(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getInt(4)));
                }while (cursor.moveToNext());
                database.setTransactionSuccessful();
            }
        } catch (Exception e){
            Log.e(TAG,"getListS: "+e);
        } finally {
            database.endTransaction();
        }
        return list;
    }


    public boolean AddSach(Sach sach){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tensach",sach.getTensach());
        values.put("theloai",sach.getTheloai());
        values.put("tacgia",sach.getTacgia());
        values.put("soluongs",sach.getSoluongs());
        long check = database.insert("Sach",null,values);
        return check != -1;
    }
    public  boolean DeteleSach(int id){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int check = database.delete("Sach","masach=?",new String[]{String.valueOf(id)});
        return check != -1;
    }

    public  boolean UpdateSach(Sach sach){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenSach",sach.getTensach());
        values.put("theloai",sach.getTheloai());
        values.put("tacgia",sach.getTacgia());
        values.put("soluongs",sach.getSoluongs());
        int check = database.update("Sach",values,"masach=?",new String[]{String.valueOf(sach.getMaSach())});
        return check != -1;
    }
}
