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
import java.util.List;

import Model.SanPham;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamViewholder> {
    private List<SanPham> ds = new ArrayList<>();
    private final Context context;

    public SanPhamAdapter(Context context, List<SanPham> list) {
        this.context = context;
        this.ds = list;
    }

    public void update(int position){
        notifyItemChanged(position);
    }

    public void add(SanPham sp){
        ds.add(sp);
        notifyItemInserted(ds.size() - 1);
    }
    public void remove(int position){
        ds.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public SanPhamViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_san_pham_viewholder, null);
        return new SanPhamViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewholder holder, int position) {
        SanPham sp = ds.get(holder.getAdapterPosition());
        //gắn vị  trí index
        holder.tv_id.setText(sp.getMasp() + "");
        holder.tv_tentp.setText(sp.getTentp());
        holder.tv_theloai.setText(sp.getTheloai());
        holder.tv_soluong.setText(sp.getSoluong() + "");
        holder.tv_dongia.setText(sp.getDongia() + "");

        holder.bt_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int masp = ds.get(holder.getAdapterPosition()).getMasp();
                ((SanPhamActivity) context).xoaSanPham(masp, holder.getAdapterPosition());
            }
        });
        holder.bt_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham sp = ds.get(holder.getAdapterPosition());
                ((SanPhamActivity) context).suaSanPham(sp, holder.getAdapterPosition());
                //recyclerview sử dụng holder.getAdapterPosition()) thay vì position
            }
        });
    }

    @Override
    public int getItemCount() {
        return ds.size();
    }
}