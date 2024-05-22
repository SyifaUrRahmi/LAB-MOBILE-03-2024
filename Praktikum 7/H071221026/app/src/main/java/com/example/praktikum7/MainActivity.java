package com.example.praktikum7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et_nimlogin, et_paslogin;
    Button btn_login, btn_regist1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            startActivity(new Intent(this, BerandaActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        // Mendapatkan mode tampilan terakhir
        SharedPreferences sharedPreferencesDark = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean isDarkMode = sharedPreferencesDark.getBoolean("isDarkMode", false);

        // Mengatur mode tampilan
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        et_nimlogin = findViewById(R.id.ET_nim);
        et_paslogin = findViewById(R.id.ET_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_regist1 = findViewById(R.id.btn_register1);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nimLog = et_nimlogin.getText().toString();
                String passLog = et_paslogin.getText().toString();

                if (passLog.isEmpty() || nimLog.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Isi semua kolom terlebih dahulu", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                String savedNim = sharedPreferences.getString("nim", "");
                String savedPassword = sharedPreferences.getString("password", "");

                if (savedNim.equals(nimLog) && savedPassword.equals(passLog)) {
                    Toast.makeText(MainActivity.this, "Login berhasil", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.apply();

                    Intent intent = new Intent(MainActivity.this, BerandaActivity.class);
                    intent.putExtra("nim", nimLog);
                    startActivity(intent);
                    finish();
                } else {
                    // Login gagal
                    Toast.makeText(MainActivity.this, "Login gagal, coba lagi", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_regist1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}