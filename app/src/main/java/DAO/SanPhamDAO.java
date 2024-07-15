package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import Database.myhelper;
import Model.SanPham;

public class SanPhamDAO {
    public myhelper helper;
    public SanPhamDAO(Context c){helper=new myhelper(c);}
    public void themSanPham(SanPham sp){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put("tentp",sp.tentp);
        value.put("tentg",sp.tentg);
        value.put("soluong",sp.soluong);
        value.put("dongia",sp.dongia);

        db.insert("sanpham",null,value);
    }


    public ArrayList<SanPham> xemSP(){
        ArrayList<SanPham>ds= new ArrayList<SanPham>();
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor c=db.rawQuery("select * from sanpham",null);
        if(c.moveToFirst()){
            do {
                int masp = c.getInt(0);
                String tensp  = c.getString(1);
                String tentg =c.getString(2);
                int soluong = c.getInt(3);
                int dongia = c.getInt(4);

                SanPham sp = new SanPham(masp,tensp,tentg, soluong,dongia);
                ds.add(sp);
            }while(c.moveToNext());

        }

        return ds;
    }
    public void xoaSanPham(int masp){
        SQLiteDatabase db=helper.getReadableDatabase();
        db.delete("sanpham","masp=?",new String[]{masp+""});
    }
}
