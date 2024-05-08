package com.example.praktikum7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class BerandaActivity extends AppCompatActivity {
    TextView TV_welcome;
    Button btn_logout, btn_setting;

    String nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        SharedPreferences sharedPreferencesDark = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean isDarkMode = sharedPreferencesDark.getBoolean("isDarkMode", false);

        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        TV_welcome = findViewById(R.id.tv_welcome);
        btn_logout = findViewById(R.id.btn_logout);
        btn_setting = findViewById(R.id.btn_setting);

        nama = getIntent().getStringExtra("nim");

        TV_welcome.setText("Selamat datang " + nama);

        btn_logout.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("isLoggedIn");
            editor.apply();

            Intent intent = new Intent(BerandaActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        btn_setting.setOnClickListener(view -> {
            Intent intent1 = new Intent(BerandaActivity.this, SettingActivity.class);
            startActivity(intent1);
        });


    }

//    public void onBackPressed() {
//        super.onBackPressed();
//        new AlertDialog.Builder(this)
//                .setTitle("Keluar dari Aplikasi")
//                .setMessage("Apakah Anda yakin ingin keluar?")
//                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        finishAffinity();
//                    }
//                })
//                .setNegativeButton("Tidak", null)
//                .show();
//    }public void onBackPressed() {
//        super.onBackPressed();
//        new AlertDialog.Builder(this)
//                .setTitle("Keluar dari Aplikasi")
//                .setMessage("Apakah Anda yakin ingin keluar?")
//                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        finishAffinity();
//                    }
//                })
//                .setNegativeButton("Tidak", null)
//                .show();
//    }
}