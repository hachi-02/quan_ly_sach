package com.example.duanmau.activity.dao;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.activity.database.DbHelper;
import com.example.duanmau.activity.model.PhieuMuon;


import java.util.ArrayList;

public class PhieuMuonDAO {
    private static DbHelper dbHelper;


    public PhieuMuonDAO(Context context) {
        dbHelper = new DbHelper(context);
    }
    public static ArrayList<PhieuMuon> getListPM() {
        ArrayList<PhieuMuon> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try {
            Cursor cursor = database.rawQuery("select * from PhieuMuon",null);
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                do {
                    list.add(new PhieuMuon(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getInt(3),
                            cursor.getString(4)));
                }while (cursor.moveToNext());
                database.setTransactionSuccessful();
            }
        } catch (Exception e){
            Log.e(TAG,"getListPM: "+e);
        } finally {
            database.endTransaction();
        }
        return list;
    }
    public boolean AddPhieuMuon(PhieuMuon phieuMuon){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maPM",phieuMuon.getMaPM());
        values.put("tensachPM",phieuMuon.getTensachPM());
        values.put("tenPM",phieuMuon.getTenPM());
        values.put("soluongPM",phieuMuon.getSoluongPM());
        values.put("ngaythue",phieuMuon.getNgaythue());
        long check = database.insert("PhieuMuon",null,values);
        return check != -1;
    }
    public  boolean DetelePhieuMuon(int id){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int check = database.delete("PhieuMuon","maPM=?",new String[]{String.valueOf(id)});
        return check != -1;
    }

    public  boolean UpdatePhieuMuon(PhieuMuon phieuMuon){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maPM",phieuMuon.getMaPM());
        values.put("tensachPM",phieuMuon.getTensachPM());
        values.put("tenPM",phieuMuon.getTenPM());
        values.put("soluongPM",phieuMuon.getSoluongPM());
        values.put("ngaythue",phieuMuon.getNgaythue());
        int check = database.update("PhieuMuon",values,"maPM=?",new String[]{String.valueOf(phieuMuon.getMaPM())});
        return check != -1;
    }
}
