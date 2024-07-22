package phieumuon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import dangnhap.dang_nhap;
import ds_sanpham.SanPhamActivity;

public class PhieuMuonActivity extends AppCompatActivity {
    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    RecyclerView rcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_phieumuon);

        rcv = findViewById(R.id.rcv);
        toolbar = findViewById(R.id.toolBar);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);


        //menu
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.et_qly_sach) {
                Intent intent = new Intent(PhieuMuonActivity.this, SanPhamActivity.class);
                startActivity(intent);
                return true;
            }
            if(id==R.id.et_qly_phieumuon){
                Toast.makeText(this, "Bạn đang ở trang Phiếu Mượn.", Toast.LENGTH_SHORT).show();
            }
            if (id == R.id.et_dandgxuat) {
                handleLogout();
                return true;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }







    //Đăng xuất
    private void handleLogout() {
        Intent intent = new Intent(PhieuMuonActivity.this, dang_nhap.class);
        startActivity(intent);
        finish();
    }
}
