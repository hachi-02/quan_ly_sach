package com.example.duanmau.activity.dao;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.activity.database.DbHelper;
import com.example.duanmau.activity.model.ThuThu;


import java.util.ArrayList;

public class ThuThuDAO {
    private static DbHelper dbHelper;


    public ThuThuDAO(Context context) {
        dbHelper = new DbHelper(context);
    }
    public static ArrayList<ThuThu> getListTT() {
        ArrayList<ThuThu> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try {
            Cursor cursor = database.rawQuery("select * from ThuThu",null);
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                do {
                    list.add(new ThuThu(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2)));
                }while (cursor.moveToNext());
                database.setTransactionSuccessful();
            }
        } catch (Exception e){
            Log.e(TAG,"getListTT: "+e);
        } finally {
            database.endTransaction();
        }
        return list;
    }
    public boolean AddThuThu(ThuThu thuThu){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten",thuThu.getTenTT());
        values.put("namsinh",thuThu.getNamSinh());
        long check = database.insert("ThuThu",null,values);
        return check != -1;
    }
    public static boolean DeteleThuThu(int id){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int check = database.delete("ThuThu","maTT=?",new String[]{String.valueOf(id)});
        return check != -1;
    }

    public  boolean UpdateThuThu(ThuThu thuThu){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten",thuThu.getTenTT());
        values.put("namSinh",thuThu.getNamSinh());
        int check = database.update("ThuThu",values,"maTT=?",new String[]{String.valueOf(thuThu.getMaTT())});
        return check != -1;
    }
}
