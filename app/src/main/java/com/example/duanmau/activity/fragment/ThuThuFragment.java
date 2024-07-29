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
import com.example.duanmau.activity.adapter.ThuThuAdapter;
import com.example.duanmau.activity.dao.LoaiSachDAO;
import com.example.duanmau.activity.dao.ThanhVienDAO;
import com.example.duanmau.activity.dao.ThuThuDAO;
import com.example.duanmau.activity.model.LoaiSach;

import com.example.duanmau.activity.model.ThanhVien;
import com.example.duanmau.activity.model.ThuThu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class ThuThuFragment extends Fragment {
    private RecyclerView recyclerView;
    private ThuThuAdapter adapter;
    private FloatingActionButton floattingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentthuthu,container,false);
        floattingActionButton = view.findViewById(R.id.addTT);
        adapter = new ThuThuAdapter(getContext(), this);
        recyclerView = view.findViewById(R.id.recyclerTT);
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
        View view = inflater.inflate(R.layout.dialog_add_tt,null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();

        TextInputEditText TenTT = view.findViewById(R.id.TenTTadd);
        TextInputEditText namsinhTT = view.findViewById(R.id.NamsinhTTadd);
        Button AddTT = view.findViewById(R.id.AddTT);

        AddTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenTT = TenTT.getText().toString().trim();
                String NamsinhTT = namsinhTT.getText().toString();

                ThuThuDAO thuThuDAO = new ThuThuDAO(getContext());
                ThuThu thuThu = new ThuThu(tenTT, NamsinhTT);
                if(thuThuDAO.AddThuThu(thuThu)){
                    adapter.addThuThu();
                    adapter.notifyDataSetChanged();
                }
                alertDialog.cancel();
            }
        });

    }
    public void showdaiglogUpdate(ThuThu thuThu, int position) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update_tt,null);
        builder.setView(view);

        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();

        TextInputEditText TenTT = view.findViewById(R.id.TenUpTT);
        TextInputEditText Namsinh = view.findViewById(R.id.namsinhUpTT);
        Button updateTT = view.findViewById(R.id.updateTT);
        Button cancelTT = view.findViewById(R.id.cancelTT);

        TenTT.setText(thuThu.getTenTT());
        Namsinh.setText(String.valueOf(thuThu.getNamSinh()));

        cancelTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        updateTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Tentt,Namsinh1;


                Tentt = TenTT.getText().toString();
                Namsinh1 =  Namsinh.getText().toString();

                thuThu.setTenTT(Tentt);
                thuThu.setNamSinh(Namsinh1);


                adapter.UpdateThuThu(thuThu, position);
                alertDialog.dismiss();
            }
        });
        cancelTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }
}