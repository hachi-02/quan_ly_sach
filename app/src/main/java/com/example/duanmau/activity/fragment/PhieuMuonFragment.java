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
import com.example.duanmau.activity.adapter.PhieuMuonAdapter;
import com.example.duanmau.activity.dao.PhieuMuonDAO;
import com.example.duanmau.activity.dao.SachDAO;

import com.example.duanmau.activity.model.PhieuMuon;
import com.example.duanmau.activity.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class PhieuMuonFragment extends Fragment {
    private RecyclerView recyclerView;
    private PhieuMuonAdapter adapter;
    private FloatingActionButton floattingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentphieumuon,container,false);
        floattingActionButton = view.findViewById(R.id.addPM);
        adapter = new PhieuMuonAdapter(getContext(), this);
        recyclerView = view.findViewById(R.id.recyclerPM);
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
        View view = inflater.inflate(R.layout.dialog_add_pm,null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();

        TextInputEditText TenSachPM = view.findViewById(R.id.TenSachPMadd);
        TextInputEditText TenPM = view.findViewById(R.id.TenPMadd);
        TextInputEditText SoluongPM = view.findViewById(R.id.SoluongPMadd);
        TextInputEditText NgaythuePM = view.findViewById(R.id.NTPMadd);
        Button AddPM = view.findViewById(R.id.AddPM);

        AddPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensachPM = TenSachPM.getText().toString().trim();
                String tenPM = TenPM.getText().toString();
                int soluongPM = Integer.parseInt(SoluongPM.getText().toString());
                String ngaythuePM = NgaythuePM.getText().toString();

                PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(getContext());
                if(phieuMuonDAO.AddPhieuMuon(new PhieuMuon(tensachPM, tenPM, Integer.valueOf(soluongPM), ngaythuePM))){
                    adapter.addPhieuMuon();
                    adapter.notifyDataSetChanged();
                }
                alertDialog.cancel();
            }
        });

    }
    public void showdaiglogUpdate(PhieuMuon phieuMuon, int position) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update_pm,null);
        builder.setView(view);

        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();

        TextInputEditText TenSupPM = view.findViewById(R.id.TenSUpPM);
        TextInputEditText TenupPM = view.findViewById(R.id.TenupPM);
        TextInputEditText SLPMup = view.findViewById(R.id.SLPMup);
        TextInputEditText NTPMup = view.findViewById(R.id.NTPMup);
        Button updatelPMup = view.findViewById(R.id.updatePMup);
        Button cancelPMup = view.findViewById(R.id.cancelPMup);

        TenSupPM.setText(phieuMuon.getTensachPM());
        TenupPM.setText(phieuMuon.getTenPM());
        SLPMup.setText(String.valueOf(phieuMuon.getSoluongPM()));
        NTPMup.setText(phieuMuon.getNgaythue());


        cancelPMup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        updatelPMup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Tensachpm,Tenpm,Ngaythuepm;
                int Soluongpm;


                Tensachpm = TenSupPM.getText().toString();
                Tenpm =  TenupPM.getText().toString();
                Soluongpm = Integer.parseInt(SLPMup.getText().toString());
                Ngaythuepm = NTPMup.getText().toString();


                phieuMuon.setTensachPM(Tensachpm);
                phieuMuon.setTenPM(Tenpm);
                phieuMuon.setSoluongPM(Soluongpm);
                phieuMuon.setNgaythue(Ngaythuepm);


                adapter.UpdatePhieuMuon(phieuMuon, position);
                alertDialog.dismiss();
            }
        });
        cancelPMup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }
}