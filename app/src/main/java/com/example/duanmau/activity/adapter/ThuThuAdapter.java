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
import com.example.duanmau.activity.dao.ThanhVienDAO;
import com.example.duanmau.activity.dao.ThuThuDAO;
import com.example.duanmau.activity.fragment.ThanhVienFragment;
import com.example.duanmau.activity.fragment.ThuThuFragment;
import com.example.duanmau.activity.model.ThanhVien;
import com.example.duanmau.activity.model.ThuThu;


import java.util.ArrayList;

public class ThuThuAdapter extends RecyclerView.Adapter<ThuThuAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ThuThu> list;
    private ThuThuDAO thuThuDAO;
    private ThuThuFragment thuThuFragment;

    public ThuThuAdapter(Context context, ThuThuFragment thuThuFragment) {
        thuThuDAO = new ThuThuDAO(context);
        list = ThuThuDAO.getListTT();
        this.context = context;
        this.thuThuFragment = thuThuFragment;
    }

    @NonNull
    @Override
    public ThuThuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thuthu,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThuThuAdapter.ViewHolder holder, int position) {
        holder.MaTT.setText("TT"+list.get(position).getMaTT());
        holder.TenTT.setText(list.get(position).getTenTT());
        holder.NamSinh.setText(String.valueOf(list.get(position).getNamSinh()));

        holder.deleteTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ThuThuDAO.DeteleThuThu(list.get(holder.getAdapterPosition()).getMaTT())){
                    list.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.updateTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thuThuFragment.showdaiglogUpdate(list.get(holder.getAdapterPosition()),holder.getAdapterPosition());
            }
        });
    }

    public void UpdateThuThu(ThuThu thuThu, int position){
        if (thuThuDAO.UpdateThuThu(thuThu)){
            list.set(position,thuThu);
            notifyItemChanged(position,thuThu);
        }
    }

    public void addThuThu(){
        list.clear();
        list.addAll(thuThuDAO.getListTT());
        notifyItemInserted(list.size()-1);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView MaTT,TenTT,NamSinh;
        ImageView deleteTT,updateTT;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            MaTT = itemView.findViewById(R.id.MaTT);
            TenTT = itemView.findViewById(R.id.TenTT);
            NamSinh = itemView.findViewById(R.id.namsinhTT);
            deleteTT = itemView.findViewById(R.id.deleteTT);
            updateTT = itemView.findViewById(R.id.updateTT);
        }
    }
}