package com.example.duanmau.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmau.R;

public class ChaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chao);

        new CountDownTimer(3000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent( ChaoActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }.start();


    }
    }

