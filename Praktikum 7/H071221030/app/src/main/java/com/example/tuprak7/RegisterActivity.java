package com.example.tuprak7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {
    private EditText inputNim, inputPassword;
    private Button buttonRegister;
    SharedPreferences sharedPreferences, sharedPreferencesTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputNim = findViewById(R.id.inputNim);
        inputPassword = findViewById(R.id.inputPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        sharedPreferencesTheme = getSharedPreferences("theme", MODE_PRIVATE);
        boolean isDark = sharedPreferencesTheme.getBoolean("isDark", false);
        SettingActivity.setDarkTheme(isDark);

        buttonRegister.setOnClickListener(v -> {
            String nim = inputNim.getText().toString();
            String password = inputPassword.getText().toString();

            if (!nim.isEmpty() && !password.isEmpty()) {
                editor.clear();

                editor.putString("nim", nim);
                editor.putString("password", password);
                editor.apply();
                Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();

                finish();
            } else {
                Toast.makeText(this, "Inputan tidak lengkap", Toast.LENGTH_SHORT).show();
            }
        });
    }
}