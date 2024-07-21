package ds_sanpham;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;

import java.util.ArrayList;

import Model.SanPham;


public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamViewholder> {
    ArrayList<SanPham> ds ;
    Context context;
    private String[] theLoaiArray =new String[]{"0","Hài hước","Manga","Khoa học"};

    public SanPhamAdapter(Context context, ArrayList<SanPham> ds) {
        this.context = context;
        this.ds = ds;
    }
    public SanPhamViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_san_pham_viewholder, parent, false);
        return new SanPhamViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewholder holder,@SuppressLint("RecyclerView") int position) {
        SanPham sp = ds.get(position);
        //gắn vị  trí index
        holder.tv_id.setText(sp.masp+"");
        holder.tv_tentp.setText(sp.tentp+"");
        if (sp.theloai >= 0 && sp.theloai < theLoaiArray.length) {
            holder.tv_theloai.setText(theLoaiArray[sp.theloai]);
        }
        holder.tv_soluong.setText(sp.soluong+"");
        holder.tv_dongia.setText(sp.dongia+"");




        holder.bt_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int masp = ds.get(position).masp;
                ((SanPhamActivity) context).xoaSanPham(masp);
            }
        });
        holder.bt_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham sp=ds.get(position);
                ((SanPhamActivity)context).suaSanPham(sp);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ds.size();
    }
}