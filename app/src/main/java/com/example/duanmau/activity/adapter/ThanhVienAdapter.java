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
import com.example.duanmau.activity.dao.LoaiSachDAO;
import com.example.duanmau.activity.dao.ThanhVienDAO;
import com.example.duanmau.activity.fragment.LoaiSachFragment;
import com.example.duanmau.activity.fragment.ThanhVienFragment;
import com.example.duanmau.activity.model.LoaiSach;
import com.example.duanmau.activity.model.ThanhVien;


import java.util.ArrayList;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ThanhVien> list;
    private ThanhVienDAO thanhVienDAO;
    private ThanhVienFragment thanhVienFragment;

    public ThanhVienAdapter(Context context, ThanhVienFragment thanhvienFragment) {
        thanhVienDAO = new ThanhVienDAO(context);
        list = ThanhVienDAO.getListTV();
        this.context = context;
        this.thanhVienFragment = thanhvienFragment;
    }

    @NonNull
    @Override
    public ThanhVienAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thanhvien,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienAdapter.ViewHolder holder, int position) {
        holder.MaTV.setText("TV"+list.get(position).getMaTV());
        holder.TenTV.setText(list.get(position).getHoten());
        holder.SDT.setText(String.valueOf(list.get(position).getSdt()));

        holder.deleteTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (thanhVienDAO.DeteleThanhVien(list.get(holder.getAdapterPosition()).getMaTV())){
                    list.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.updateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thanhVienFragment.showdaiglogUpdate(list.get(holder.getAdapterPosition()),holder.getAdapterPosition());
            }
        });
    }

    public void UpdateThanhVien(ThanhVien thanhVien, int position){
        if (thanhVienDAO.UpdateThanhVien(thanhVien)){
            list.set(position,thanhVien);
            notifyItemChanged(position,thanhVien);
        }
    }

    public void addThanhVien(){
        list.clear();
        list.addAll(thanhVienDAO.getListTV());
        notifyItemInserted(list.size()-1);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView MaTV,TenTV,SDT;
        ImageView deleteTV,updateTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            MaTV = itemView.findViewById(R.id.MaTV);
            TenTV = itemView.findViewById(R.id.TenTV);
            SDT = itemView.findViewById(R.id.SDT);
            deleteTV = itemView.findViewById(R.id.deleteTV);
            updateTV = itemView.findViewById(R.id.updateTV);
        }
    }
}