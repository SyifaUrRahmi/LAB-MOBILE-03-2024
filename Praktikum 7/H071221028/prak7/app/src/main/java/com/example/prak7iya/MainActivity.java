package com.example.prak7iya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    TextView welcome_tv;
    Button logout_button, setting_button;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         welcome_tv = findViewById(R.id.welcome_tv);
         logout_button = findViewById(R.id.logout_button);
         setting_button = findViewById(R.id.Setting_button);

         sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

         String nim = sharedPreferences.getString("NIM","") ;

         //ambil data untuk selamat datang
        welcome_tv.setText("selamat datang" + " " + nim);
    }


    //untuk clear login, logout
    public void onLogoutButtonClick(View view){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();

        Toast.makeText(this, "Logout successful!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent (MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

    public void onSettingButtonClick(View view){
        Intent intent = new Intent (MainActivity.this, SettingActivity.class);
        startActivity(intent);
    }



}