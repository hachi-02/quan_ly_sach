package com.example.duanmau.activity.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;
import com.example.duanmau.activity.dao.PhieuMuonDAO;
import com.example.duanmau.activity.fragment.PhieuMuonFragment;
import com.example.duanmau.activity.model.PhieuMuon;


import java.util.ArrayList;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolder> {
    private Context context;
    private ArrayList<PhieuMuon> list;
    private PhieuMuonDAO phieuMuonDAO;
    private PhieuMuonFragment phieuMuonFragment;

    public PhieuMuonAdapter(Context context, PhieuMuonFragment phieuMuonFragment) {
        phieuMuonDAO = new PhieuMuonDAO(context);
        list = PhieuMuonDAO.getListPM();
        this.context = context;
        this.phieuMuonFragment = phieuMuonFragment;
    }

    @NonNull
    @Override
    public PhieuMuonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phieumuon,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhieuMuonAdapter.ViewHolder holder, int position) {
        holder.MaPM.setText("PM"+list.get(position).getMaPM());
        holder.TenSach.setText(list.get(position).getTensachPM());
        holder.TenPM.setText(list.get(position).getTenPM());
        holder.SoluongPM.setText(String.valueOf(list.get(position).getSoluongPM()));
        holder.NgaythuePM.setText(list.get(position).getNgaythue());

        holder.deletePM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phieuMuonDAO.DetelePhieuMuon(list.get(holder.getAdapterPosition()).getMaPM())){
                    list.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.updatePM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phieuMuonFragment.showdaiglogUpdate(list.get(holder.getAdapterPosition()),holder.getAdapterPosition());
            }
        });
    }

    public void UpdatePhieuMuon(PhieuMuon phieuMuon, int position){
        if (phieuMuonDAO.UpdatePhieuMuon(phieuMuon)){
            list.set(position,phieuMuon);
            notifyItemChanged(position,phieuMuon);
        }
    }

    public void addPhieuMuon(){
        list.clear();
        list.addAll(phieuMuonDAO.getListPM());
        notifyItemInserted(list.size()-1);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView MaPM,TenSach,TenPM,SoluongPM,NgaythuePM;
        ImageView deletePM,updatePM;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            MaPM = itemView.findViewById(R.id.MaPM);
            TenSach = itemView.findViewById(R.id.TSPM);
            TenPM = itemView.findViewById(R.id.TenPM);
            SoluongPM = itemView.findViewById(R.id.SLPM);
            NgaythuePM = itemView.findViewById(R.id.NgaythuePM);
            deletePM = itemView.findViewById(R.id.deletePM);
            updatePM = itemView.findViewById(R.id.updatePM);
        }
    }
}