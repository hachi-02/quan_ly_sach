package ds_sanpham;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import DAO.SanPhamDAO;
import Model.SanPham;

public class SanPhamActivity extends AppCompatActivity {
    RecyclerView rcv;
    LinearLayout bt_account;
    SanPhamDAO spd;
    ArrayList<SanPham> ds;
    SanPhamAdapter adapter;
    FloatingActionButton fabutton;
    EditText et_ten,et_soluong,et_gia,et_theloai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_san_pham);
        rcv = findViewById(R.id.rcv);
        bt_account = findViewById(R.id.bt_account);
        fabutton = findViewById(R.id.floatactionbutton);


        spd = new SanPhamDAO(SanPhamActivity.this);
//        sp.themSanPham(new SanPham("samsung", "truyện kiều", 5, 3000));
//        sp.themSanPham(new SanPham("samsung", "truyện kiều", 5, 3000));

        dulieu();

        fabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogthemSanPham();
            }
        });
    }

    public void dulieu()
    {
        ds = spd.xemSP();
        SanPhamAdapter adapter =new SanPhamAdapter(SanPhamActivity.this,ds);
        LinearLayoutManager linear = new LinearLayoutManager(SanPhamActivity.this);
        rcv.setLayoutManager(linear);
        rcv.setAdapter(adapter);
    }





    public void dialogthemSanPham() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SanPhamActivity.this);
        LayoutInflater inf = getLayoutInflater();
        View v = inf.inflate(R.layout.them_sua, null);
        builder.setView(v);
        et_ten=v.findViewById(R.id.ten);
        et_theloai=v.findViewById(R.id.theloai);
        et_soluong=v.findViewById(R.id.soluong);
        et_gia=v.findViewById(R.id.giaban);

        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String ten=et_ten.getText().toString();
                String theloai=et_theloai.getText().toString();
                int soluong=Integer.parseInt(et_soluong.getText().toString());
                int dongia=Integer.parseInt(et_gia.getText().toString());

                SanPham sp=new SanPham(ten,theloai,dongia,soluong);
                spd.themSanPham(sp);
                dulieu();
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
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
                    spd.xoaSanPham(masp);
                    dulieu();
                    Toast.makeText(getApplicationContext(), "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                }
            });

            AlertDialog dialog=builder.create();
            dialog.show();

    }
    public void suaSanPham(SanPham sp){
        AlertDialog.Builder builder = new AlertDialog.Builder(SanPhamActivity.this);
        LayoutInflater inf= getLayoutInflater();
        View v=inf.inflate(R.layout.them_sua,null);
        builder.setView(v);
        et_ten=v.findViewById(R.id.ten);
        et_theloai=v.findViewById(R.id.theloai);
        et_soluong=v.findViewById(R.id.soluong);
        et_gia=v.findViewById(R.id.giaban);

        et_ten.setText(sp.tentp);
        et_theloai.setText(sp.theloai);
        et_soluong.setText(sp.soluong+"");
        et_gia.setText(sp.dongia+"");

        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String ten= et_ten.getText().toString();
                String theloai= et_theloai.getText().toString();
                int giaban=Integer.parseInt(et_gia.getText().toString());
                int soluong=Integer.parseInt(et_soluong.getText().toString());

                SanPham spnew=new SanPham(sp.masp,ten,theloai,giaban,soluong);
                spd.themSanPham(spnew);
                dulieu();
            }
        });


        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog=builder.create();
        dialog.show();
    }





}