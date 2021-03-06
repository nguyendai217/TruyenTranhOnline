package com.example.truyentranhonline.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemClock.sleep(2000); // dừng hệ thống hoạt đọng lại sau 2s
        // chuyển màn hình sang home
        Intent intent= new Intent(SplashActivity.this, NavigationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // killed màn hình splash đi
    }
}
