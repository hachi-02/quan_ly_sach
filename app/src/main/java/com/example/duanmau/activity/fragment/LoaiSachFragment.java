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
import com.example.duanmau.activity.dao.LoaiSachDAO;
import com.example.duanmau.activity.model.LoaiSach;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoaiSachFragment extends Fragment {
    private RecyclerView recyclerView;
    private LoaiSachAdapter adapter;
    private FloatingActionButton floattingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentloaisach,container,false);
        floattingActionButton = view.findViewById(R.id.addLS);
        adapter = new LoaiSachAdapter(getContext(), this);
        recyclerView = view.findViewById(R.id.recyclerLS1);
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
        View view = inflater.inflate(R.layout.dialog_add,null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();

        TextInputEditText edtTenLS = view.findViewById(R.id.edtTenAdd);
        TextInputEditText edtSLLS = view.findViewById(R.id.edtSoLuongAdd);
        Button btnAdd = view.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenLS = edtTenLS.getText().toString().trim();
                int soluongLS = Integer.parseInt(edtSLLS.getText().toString());

                LoaiSachDAO loaiSachDAO = new LoaiSachDAO(getContext());
                LoaiSach loaiSach = new LoaiSach(tenLS, String.valueOf(soluongLS));
                if(loaiSachDAO.AddLoaiSach(loaiSach)){
                    adapter.addLoaiSach();
                    adapter.notifyDataSetChanged();
                }
                alertDialog.cancel();
            }
        });

    }
    public void showdaiglogUpdate(LoaiSach loaiSach, int position) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update,null);
        builder.setView(view);

        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();

        TextInputEditText editTensp = view.findViewById(R.id.edtTenUp);
        TextInputEditText editSoluong = view.findViewById(R.id.edtSoLuongUp);
        Button btnupdate = view.findViewById(R.id.btnupdate);
        Button btncancel = view.findViewById(R.id.btncancel);

        editTensp.setText(loaiSach.getTenloai());
        editSoluong.setText(String.valueOf(loaiSach.getSoluong()));

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name;
                int soluong;

                name = editTensp.getText().toString();
                soluong = Integer.parseInt(editSoluong.getText().toString());

                loaiSach.setTenloai(name);
                loaiSach.setSoluong(String.valueOf(soluong));


                adapter.UpdateLoaiSach(loaiSach, position);
                alertDialog.dismiss();
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }
}