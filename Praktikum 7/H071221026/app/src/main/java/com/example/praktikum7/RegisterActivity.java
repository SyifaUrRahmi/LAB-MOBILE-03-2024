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

public class RegisterActivity extends AppCompatActivity {
    EditText et_nimRegist, et_passRegist;
    Button btn_regist2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        SharedPreferences sharedPreferencesDark = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean isDarkMode = sharedPreferencesDark.getBoolean("isDarkMode", false);

        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        et_nimRegist= findViewById(R.id.ET_nimRegist);
        et_passRegist= findViewById(R.id.ET_passRegist);
        btn_regist2 = findViewById(R.id.btn_register2);

        btn_regist2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nimReg = et_nimRegist.getText().toString();
                String passReg = et_passRegist.getText().toString();
                if(nimReg.isEmpty()||passReg.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Isi semua kolom terlebih dahulu", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                String savedNim = sharedPreferences.getString("nim","");


                if (!savedNim.isEmpty() && !savedNim.equals(nimReg)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("nim");
                    editor.remove("password");
                    editor.apply();
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nim", nimReg);
                editor.putString("password", passReg);
                editor.apply();

                Toast.makeText(RegisterActivity.this, "Registrasi berhasil", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}