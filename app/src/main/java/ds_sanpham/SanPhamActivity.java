package ds_sanpham;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Objects;

import DAO.SanPhamDAO;
import Model.SanPham;

public class SanPhamActivity extends AppCompatActivity {
    RecyclerView rcv;
    LinearLayout bt_account, bt_them;
    SanPhamDAO spd;
    ArrayList<SanPham> ds;
    SanPhamAdapter adapter;
    FloatingActionButton fabutton;
    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_san_pham);
        rcv = findViewById(R.id.rcv);
//        bt_account = findViewById(R.id.bt_account);
        fabutton = findViewById(R.id.floatactionbutton);
        toolbar = findViewById(R.id.toolBar);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        ActionBar actionBar = getSupportActionBar();

        ds = new ArrayList<SanPham>();
        spd = new SanPhamDAO(SanPhamActivity.this);
        ds = spd.xemSP();
//        sp.themSanPham(new SanPham("samsung", "truyện kiều", 5, 3000));
//        sp.themSanPham(new SanPham("samsung", "truyện kiều", 5, 3000));
        dulieu();
        adapter = new SanPhamAdapter(SanPhamActivity.this, ds);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(adapter);


        fabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themSanPham();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            drawerLayout.openDrawer(GravityCompat.START);
        }
            return super.onOptionsItemSelected(item);
    }

    public void dulieu() {
        ds = spd.xemSP();
        SanPhamAdapter adapter = new SanPhamAdapter(SanPhamActivity.this, ds);
        LinearLayoutManager linear = new LinearLayoutManager(SanPhamActivity.this);
        rcv.setLayoutManager(linear);
        rcv.setAdapter(adapter);
    }

    public void themSanPham() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SanPhamActivity.this);
        LayoutInflater inf = getLayoutInflater();
        View v = inf.inflate(R.layout.dialogthemsanpham, null);
        builder.setView(v);
        EditText et_ten = v.findViewById(R.id.ten);
        EditText et_theloai = v.findViewById(R.id.theloai);
        EditText et_soluong = v.findViewById(R.id.soluong);
        EditText et_gia = v.findViewById(R.id.giaban);

        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String ten = et_ten.getText().toString();
                String theloai = et_theloai.getText().toString();
                int soluong = Integer.parseInt(et_soluong.getText().toString());
                int dongia = Integer.parseInt(et_gia.getText().toString());
                SanPham sp = new SanPham(ten, theloai, soluong, dongia);
                spd.themSanPham(sp);
                dulieu();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    // xoa san pham
    public void xoaSanPham(int masp, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SanPhamActivity.this);
        builder.setTitle("thong bao");
        builder.setMessage("ban co muon xoa san pham nay khong");
        builder.setCancelable(false);
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                spd.xoaSanPham(masp);
                adapter.remove(position);
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void suaSanPham(SanPham sanPham, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SanPhamActivity.this);
        LayoutInflater inf = getLayoutInflater();
        View v = inf.inflate(R.layout.dialogsuasanpham, null);
        builder.setView(v);

        EditText et_ten = v.findViewById(R.id.ten);
        EditText et_theloai = v.findViewById(R.id.theloai);
        EditText et_soluong = v.findViewById(R.id.soluong);
        EditText et_gia = v.findViewById(R.id.giaban);
        et_ten.setText(sanPham.getTentp());
        et_theloai.setText(sanPham.getTheloai());
        et_soluong.setText(sanPham.getSoluong() + "");
        et_gia.setText(sanPham.getDongia() + "");
        builder.setNegativeButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String ten = et_ten.getText().toString();
                String theloai = et_theloai.getText().toString();
                int soluong = Integer.parseInt(et_soluong.getText().toString());
                int dongia = Integer.parseInt(et_gia.getText().toString());

                SanPham spnew = new SanPham(sanPham.getMasp(), ten, theloai, soluong, dongia);
                //phải dùng getter setter, trong java ko dc truy cập trực tiếp như z
                spd.suaSanPham(spnew);
                ds.set(position, spnew);
                adapter.update(position);
            }
        });
        builder.setPositiveButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }
}