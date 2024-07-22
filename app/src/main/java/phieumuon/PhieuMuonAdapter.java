package phieumuon;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Model.PhieuMuon;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonViewholder> {
    Context c;
    ArrayList<PhieuMuon> dspm;
    public PhieuMuonAdapter(Context c,ArrayList<PhieuMuon> dspm){
        this.c=c;
        this.dspm=dspm;
    }
    @NonNull
    @Override
    public PhieuMuonViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.item_phieu_muon_viewholder,parent,false);
        return new PhieuMuonViewholder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull PhieuMuonViewholder holder, int position) {
        PhieuMuon pm=dspm.get(position);
        String ngaymuon = pm.ngaymuon.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String ngaytra = pm.ngaytra.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String trangThaiText = pm.trangthai ? "Đã trả" : "Chưa trả";
        holder.tv_mpm.setText(pm.mapm+"");
        holder.tv_masp.setText(pm.masp+"");
        holder.tv_ten_ngm.setText(pm.ten_ngm);
        holder.tv_tentp.setText(pm.tentp);
        holder.tv_sdt.setText(pm.sdt+"");
        holder.tv_ngaymuon.setText(ngaymuon);
        holder.tv_ngaytra.setText(ngaytra);
        holder.tv_trangthai.setText(trangThaiText);


    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
