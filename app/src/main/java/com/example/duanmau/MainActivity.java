package com.example.duanmau;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import ds_sanpham.SanPhamActivity;
import ds_sanpham.SanPhamFragment;
import phieumuon.PhieuMuonFragment;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    NavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        drawer=(DrawerLayout)findViewById(R.id.drawer_layout);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        navigation=(NavigationView)findViewById(R.id.nvView);

        setSupportActionBar(toolbar);

        ActionBar ab=getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        SanPhamFragment f= new SanPhamFragment();
        FragmentManager fagment=getSupportFragmentManager();
        fagment.beginTransaction()
                .replace(R.id.flContent,f)
                .commit();


        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id= item.getItemId();
                Fragment f;

                if(id==R.id.et_qly_sach){
                    f=new SanPhamFragment();
                }
                if(id== R.id.et_qly_phieumuon){
                    f=new PhieuMuonFragment();
                }
                else{
                    f=new SanPhamFragment();
                }
                FragmentManager fagment=getSupportFragmentManager();
                fagment.beginTransaction()
                        .replace(R.id.flContent,f)
                        .commit();

                setTitle(item.getTitle());
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id=item.getItemId();
        if(id == android.R.id.home){
            drawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}