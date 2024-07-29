package com.example.duanmau.activity.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;
import com.example.duanmau.activity.adapter.LoaiSachAdapter;
import com.example.duanmau.activity.adapter.ThanhVienAdapter;
import com.example.duanmau.activity.dao.LoaiSachDAO;
import com.example.duanmau.activity.dao.ThanhVienDAO;
import com.example.duanmau.activity.model.LoaiSach;

import com.example.duanmau.activity.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class ThanhVienFragment extends Fragment {
    private RecyclerView recyclerView;
    private ThanhVienAdapter adapter;
    private FloatingActionButton floattingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentthanhvien,container,false);
        floattingActionButton = view.findViewById(R.id.addTV);
        adapter = new ThanhVienAdapter(getContext(), this);
        recyclerView = view.findViewById(R.id.recyclerTV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        floattingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdailodAddLS();
            }
        });
        return view;
    }
    public void showdailodAddLS(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_tv,null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();

        TextInputEditText TenTV = view.findViewById(R.id.TenTVadd);
        TextInputEditText SDT = view.findViewById(R.id.SDTAddTV);
        Button AddTV = view.findViewById(R.id.AddTV);

        AddTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenTV = TenTV.getText().toString().trim();
                int SDTTV = Integer.parseInt(SDT.getText().toString());

                ThanhVienDAO thanhVienDAO = new ThanhVienDAO(getContext());
               ThanhVien thanhVien = new ThanhVien(tenTV, String.valueOf(SDTTV));
                if(thanhVienDAO.AddThanhVien(thanhVien)){
                    adapter.addThanhVien();
                    adapter.notifyDataSetChanged();
                }
                alertDialog.cancel();
            }
        });

    }
    public void showdaiglogUpdate(ThanhVien thanhVien, int position) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update_tv,null);
        builder.setView(view);

        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();

        TextInputEditText TenTV = view.findViewById(R.id.TenUpTV);
        TextInputEditText sdt = view.findViewById(R.id.SDTUpTV);
        Button updateTV = view.findViewById(R.id.updateTV);
        Button cancelTV = view.findViewById(R.id.cancelTV);

        TenTV.setText(thanhVien.getHoten());
        sdt.setText(String.valueOf(thanhVien.getSdt()));

        cancelTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        updateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoten,sdt1;


                hoten = TenTV.getText().toString();
                sdt1 =  sdt.getText().toString();

                thanhVien.setHoten(hoten);
                thanhVien.setSdt(sdt1);


                adapter.UpdateThanhVien(thanhVien, position);
                alertDialog.dismiss();
            }
        });
        cancelTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }
}