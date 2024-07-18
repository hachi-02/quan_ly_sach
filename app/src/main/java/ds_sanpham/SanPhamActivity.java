package ds_sanpham;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;

import java.util.ArrayList;

import DAO.SanPhamDAO;
import Model.SanPham;

public class SanPhamActivity extends AppCompatActivity {
    RecyclerView rcv;
    LinearLayout bt_account, bt_them;
    SanPhamDAO sp;
    ArrayList<SanPham> ds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_san_pham);
        rcv=findViewById(R.id.rcv);
        bt_account=findViewById(R.id.bt_account);


        ds=new ArrayList<SanPham>();
        sp=new SanPhamDAO(SanPhamActivity.this);

        sp.themSanPham(new SanPham("samsung","truyện kiều",5,3000));
        sp.themSanPham(new SanPham("samsung","truyện kiều",5,3000));
        dulieu();
    }
    public void dulieu(){
        ds=sp.xemSP();
        SanPhamAdapter adapter=new SanPhamAdapter(SanPhamActivity.this,ds);
        LinearLayoutManager lmanager=new LinearLayoutManager(SanPhamActivity.this);
        rcv.setLayoutManager(lmanager);
        rcv.setAdapter(adapter);
    }
    public void xoaSanPham(int masp){
        AlertDialog.Builder builder = new AlertDialog.Builder(SanPhamActivity.this);
        builder.setTitle("thong bao");
        builder.setMessage("Ban co muon xoa");
        builder.setCancelable(false);

        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sp.xoaSanPham(masp);
                dulieu();
                Toast.makeText(getApplicationContext(), "Xoa thanh cong", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog=builder.create();
        dialog.show();
    }
}