package ds_sanpham;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;

public class SanPhamViewholder extends RecyclerView.ViewHolder {
    TextView tv_id,tv_tentp,tv_tentg,tv_soluong,tv_dongia;
    LinearLayout bt_sua,bt_xoa;

    public SanPhamViewholder(@NonNull View itemView) {
        super(itemView);
        this.tv_id=itemView.findViewById(R.id.tv_masp);
        this.tv_tentp=itemView.findViewById(R.id.tv_tentp);
        this.tv_tentg=itemView.findViewById(R.id.tv_tentg);
        this.tv_soluong=itemView.findViewById(R.id.tv_soluong);
        this.tv_dongia=itemView.findViewById(R.id.tv_dongia);


        this.bt_sua=itemView.findViewById(R.id.bt_sua);
        this.bt_xoa=itemView.findViewById(R.id.bt_xoa);
    }

}