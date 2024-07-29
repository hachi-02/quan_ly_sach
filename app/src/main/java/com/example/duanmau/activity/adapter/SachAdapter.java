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
import com.example.duanmau.activity.dao.SachDAO;
import com.example.duanmau.activity.dao.ThanhVienDAO;
import com.example.duanmau.activity.dao.ThuThuDAO;
import com.example.duanmau.activity.fragment.SachFragment;
import com.example.duanmau.activity.fragment.ThanhVienFragment;
import com.example.duanmau.activity.fragment.ThuThuFragment;
import com.example.duanmau.activity.model.Sach;
import com.example.duanmau.activity.model.ThanhVien;
import com.example.duanmau.activity.model.ThuThu;


import java.util.ArrayList;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Sach> list;
    private SachDAO sachDAO;
    private SachFragment sachFragment;

    public SachAdapter(Context context, SachFragment sachFragment) {
        sachDAO = new SachDAO(context);
        list = SachDAO.getListS();
        this.context = context;
        this.sachFragment = sachFragment;
    }

    @NonNull
    @Override
    public SachAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sach,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachAdapter.ViewHolder holder, int position) {
        holder.MaS.setText("S"+list.get(position).getMaSach());
        holder.TenS.setText(list.get(position).getTensach());
        holder.TheloaiS.setText(list.get(position).getTheloai());
        holder.TacgiaS.setText(list.get(position).getTacgia());
        holder.SoluongS.setText(String.valueOf(list.get(position).getSoluongs()));

        holder.deleteS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sachDAO.DeteleSach(list.get(holder.getAdapterPosition()).getMaSach())){
                    list.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.updateS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sachFragment.showdaiglogUpdate(list.get(holder.getAdapterPosition()),holder.getAdapterPosition());
            }
        });
    }

    public void UpdateSach(Sach sach, int position){
        if (sachDAO.UpdateSach(sach)){
            list.set(position,sach);
            notifyItemChanged(position,sach);
        }
    }

    public void addSach(){
        list.clear();
        list.addAll(sachDAO.getListS());
        notifyItemInserted(list.size()-1);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView MaS,TenS,TheloaiS,TacgiaS,SoluongS;
        ImageView deleteS,updateS;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            MaS = itemView.findViewById(R.id.MaS);
            TenS = itemView.findViewById(R.id.TenS);
            TheloaiS = itemView.findViewById(R.id.TheloaiS);
            TacgiaS = itemView.findViewById(R.id.TacgiaS);
            SoluongS = itemView.findViewById(R.id.SoluongS);
            deleteS = itemView.findViewById(R.id.deleteS);
            updateS = itemView.findViewById(R.id.updateS);
        }
    }
}