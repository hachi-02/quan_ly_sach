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
import com.example.duanmau.activity.adapter.SachAdapter;
import com.example.duanmau.activity.dao.SachDAO;

import com.example.duanmau.activity.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class SachFragment extends Fragment {
    private RecyclerView recyclerView;
    private SachAdapter adapter;
    private FloatingActionButton floattingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentsach,container,false);
        floattingActionButton = view.findViewById(R.id.addS);
        adapter = new SachAdapter(getContext(), this);
        recyclerView = view.findViewById(R.id.recyclerS);
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
        View view = inflater.inflate(R.layout.dialog_add_sach,null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();

        TextInputEditText TenS = view.findViewById(R.id.TenSadd);
        TextInputEditText TheloaiS = view.findViewById(R.id.Theloaiadd);
        TextInputEditText TacGiaS = view.findViewById(R.id.TGadd);
        TextInputEditText SoluongS = view.findViewById(R.id.SlSadd);
        Button AddS = view.findViewById(R.id.AddS);

        AddS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensach = TenS.getText().toString().trim();
                String theloai = TheloaiS.getText().toString();
                String tacGia = TacGiaS.getText().toString();
                int soluongs = Integer.parseInt(SoluongS.getText().toString());

                SachDAO sachDAO = new SachDAO(getContext());
//                Sach sach = ;
                if(sachDAO.AddSach(new Sach(tensach, theloai, tacGia, Integer.valueOf(soluongs)))){
                    adapter.addSach();
                    adapter.notifyDataSetChanged();
                }
                alertDialog.cancel();
            }
        });

    }
    public void showdaiglogUpdate(Sach sach, int position) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update_sach,null);
        builder.setView(view);

        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();

        TextInputEditText TenSup = view.findViewById(R.id.TenUpS);
        TextInputEditText TheloaiSup = view.findViewById(R.id.TLSup);
        TextInputEditText TacgiaSup = view.findViewById(R.id.TGSup);
        TextInputEditText SoluongSup = view.findViewById(R.id.SlSup);
        Button updateSup = view.findViewById(R.id.updateSup);
        Button cancelSup = view.findViewById(R.id.cancelSup);

        TenSup.setText(sach.getTensach());
        TheloaiSup.setText(sach.getTheloai());
        TacgiaSup.setText(sach.getTacgia());
        SoluongSup.setText(String.valueOf(sach.getSoluongs()));

        cancelSup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        updateSup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Tens,Theloais,Tacgias;
                int Soluongs;


                Tens = TenSup.getText().toString();
                Theloais =  TheloaiSup.getText().toString();
                Tacgias = TacgiaSup.getText().toString();
                Soluongs = Integer.parseInt(SoluongSup.getText().toString());

                sach.setTensach(Tens);
                sach.setTheloai(Theloais);
                sach.setTacgia(Tacgias);
                sach.setSoluongs(Soluongs);


                adapter.UpdateSach(sach, position);
                alertDialog.dismiss();
            }
        });
        cancelSup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }
}