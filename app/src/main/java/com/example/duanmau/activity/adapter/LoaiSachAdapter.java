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
import com.example.duanmau.activity.fragment.LoaiSachFragment;
import com.example.duanmau.activity.model.LoaiSach;


import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder> {
    private Context context;
    private ArrayList<LoaiSach> list;
    private LoaiSachDAO loaiSachDAO;
    private LoaiSachFragment loaiSachFragment;

    public LoaiSachAdapter(Context context, LoaiSachFragment loaiSachFragment) {
        loaiSachDAO = new LoaiSachDAO(context);
        list = loaiSachDAO.getListLS();
        this.context = context;
        this.loaiSachFragment = loaiSachFragment;
    }

    @NonNull
    @Override
    public LoaiSachAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisach,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiSachAdapter.ViewHolder holder, int position) {
        holder.MaLS.setText("LS"+list.get(position).getMaLoai());
        holder.TenLS.setText(list.get(position).getTenloai());
        holder.SL.setText(String.valueOf(list.get(position).getSoluong()));

        holder.btndeleteLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loaiSachDAO.DeteleSanPham(list.get(holder.getAdapterPosition()).getMaLoai())){
                    list.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.btnedtLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loaiSachFragment.showdaiglogUpdate(list.get(holder.getAdapterPosition()),holder.getAdapterPosition());
            }
        });
    }

    public void UpdateLoaiSach(LoaiSach loaiSach, int position){
        if (loaiSachDAO.UpdateSapPham(loaiSach)){
            list.set(position,loaiSach);
            notifyItemChanged(position,loaiSach);
        }
    }

    public void addLoaiSach(){
        list.clear();
        list.addAll(loaiSachDAO.getListLS());
        notifyItemInserted(list.size()-1);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView MaLS,TenLS,SL;
        ImageView btndeleteLS,btnedtLS;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            MaLS = itemView.findViewById(R.id.MaLS);
            TenLS = itemView.findViewById(R.id.TenLS);
            SL = itemView.findViewById(R.id.SL);
            btndeleteLS = itemView.findViewById(R.id.btndeleteLS);
            btnedtLS = itemView.findViewById(R.id.btnedtLS);
        }
    }
}