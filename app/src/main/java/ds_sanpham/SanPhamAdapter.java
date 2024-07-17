package ds_sanpham;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;

import java.util.ArrayList;

import Model.SanPham;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamViewholder> {
    ArrayList<SanPham> ds;
    Context context;
    public SanPhamAdapter(Context context, ArrayList<SanPham> ds){
        this.ds=ds;
        this.context=context;
    }


    public SanPhamViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //gắn giao diện
        View v= LayoutInflater.from(context).inflate(R.layout.activity_san_pham_viewholder,parent,false);
        return new SanPhamViewholder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull SanPhamViewholder holder, @SuppressLint("RecyclerView") int position) {
        SanPham sp=ds.get(position);
        //gắn vị  trí index
        holder.tv_id.setText(sp.masp+"");
        holder.tv_tentp.setText(sp.tentp);
        holder.tv_theloai.setText(sp.theloai);
        holder.tv_soluong.setText(sp.soluong+"");
        holder.tv_dongia.setText(sp.dongia+"");

        holder.bt_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int masp=ds.get(position).masp;
                ((SanPhamActivity)context).xoaSanPham(masp);
            }
        });
    }
@Override
public int getItemCount() {
    return ds.size();
}
}