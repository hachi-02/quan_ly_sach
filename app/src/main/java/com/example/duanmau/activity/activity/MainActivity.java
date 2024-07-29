package com.example.duanmau.activity.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.duanmau.R;
import com.example.duanmau.activity.fragment.DoanhThuFragment;
import com.example.duanmau.activity.fragment.DoiMatKhauFragment;
import com.example.duanmau.activity.fragment.LoaiSachFragment;
import com.example.duanmau.activity.fragment.PhieuMuonFragment;
import com.example.duanmau.activity.fragment.SachFragment;
import com.example.duanmau.activity.fragment.ThanhVienFragment;
import com.example.duanmau.activity.fragment.ThuThuFragment;
import com.example.duanmau.activity.fragment.Top10Fragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        ActionBar actionBar = getSupportActionBar();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.linearlayout, new PhieuMuonFragment())
                .commit();
        actionBar.setTitle("Quản lý phiếu mượn");
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.PhieuMuon) {
                    fragment = new PhieuMuonFragment();
                } else if (item.getItemId() == R.id.LoaiSach) {
                    fragment = new LoaiSachFragment();
                } else if (item.getItemId() == R.id.Sach) {
                    fragment = new SachFragment();
                } else if (item.getItemId() == R.id.ThanhVien){
                    fragment = new ThanhVienFragment();
                } else if (item.getItemId() == R.id.Top10){
                    fragment = new Top10Fragment();
                } else if (item.getItemId() == R.id.DoanhThu){
                    fragment = new DoanhThuFragment();
                } else if (item.getItemId() == R.id.Doipass){
                    fragment = new DoiMatKhauFragment();
                } else if (item.getItemId() == R.id.Dangxuat){

                } else if (item.getItemId() == R.id.thuthu){
                    fragment = new ThuThuFragment();
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.linearlayout, fragment).
                        commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                actionBar.setTitle(item.getTitle());
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(() -> doubleBackToExitPressedOnce = false, 1000);
    }
}