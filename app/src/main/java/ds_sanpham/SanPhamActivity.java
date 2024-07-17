package ds_sanpham;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
        rcv = findViewById(R.id.rcv);
        bt_account = findViewById(R.id.bt_account);


        ds = new ArrayList<SanPham>();
        sp = new SanPhamDAO(SanPhamActivity.this);

        sp.themSanPham(new SanPham("samsung", "truyện kiều", 5, 3000));
        sp.themSanPham(new SanPham("samsung", "truyện kiều", 5, 3000));
        dulieu();
    }

    public void dulieu() {
        ds = sp.xemSP();
//        SanPhamAdapter adapter=new SanPhamAdapter(SanPhamActivity.this,ds);
        SanPhamAdapter adapter = new SanPhamAdapter(SanPhamActivity.this, ds);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(adapter);
    }

    public void xoaSanPham(int masp) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SanPhamActivity.this);
        builder.setTitle("thong bao");
        builder.setMessage("ban co muon xoa san pham nay khong");
        builder.setCancelable(false);

        builder.setNegativeButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                sp.xoaSanPham(masp);
                dulieu();
            }
        });

        builder.setPositiveButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void suaSanPham(SanPham sp) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SanPhamActivity.this);
        LayoutInflater inf = getLayoutInflater();
        View v = inf.inflate(R.layout.dialogsuasanpham, null);
        builder.setView(v);
        builder.show();
        EditText et_ten = v.findViewById(R.id.ten);
        EditText et_theloai = v.findViewById(R.id.theloai);
        EditText et_soluong = v.findViewById(R.id.soluong);
        EditText et_gia = v.findViewById(R.id.giaban);
        et_ten.setText(sp.getTentp());
        et_theloai.setText(sp.getTheloai());
        et_soluong.setText(sp.getSoluong() + "");
        et_gia.setText(sp.getDongia() + "");
    }
}