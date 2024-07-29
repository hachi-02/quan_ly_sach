package com.example.duanmau.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.duanmau.R;
import com.example.duanmau.activity.database.DbHelper;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //
        TextInputEditText edtUsername = findViewById(R.id.edtUsername);
        TextInputEditText edtPassword = findViewById(R.id.edtPassword);
        Button btnLogin = findViewById(R.id.btnLogin);

        //
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten_dn = edtUsername.getText().toString();
                String mk = edtPassword.getText().toString();
                DbHelper dbHelper = new DbHelper(LoginActivity.this);
                if (ten_dn.isEmpty() || mk.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Bạn chưa điền đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    if (dbHelper.login(ten_dn,mk) ==1 ){
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("tendn",ten_dn);
                        editor.apply();
//                        Lưu dữ liệu vào data với key và value
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    }else {
                        Toast.makeText(LoginActivity.this, "Thông tin đăng nhập chưa chính xác", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}