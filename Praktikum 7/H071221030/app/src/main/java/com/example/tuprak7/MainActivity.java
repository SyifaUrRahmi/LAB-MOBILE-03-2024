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

public class MainActivity extends AppCompatActivity {
    private EditText inputNim, inputPassword;
    private Button buttonLogin, buttonRegister;
    SharedPreferences sharedPreferences, sharedPreferencesTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNim = findViewById(R.id.inputNim);
        inputPassword = findViewById(R.id.inputPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        sharedPreferencesTheme = getSharedPreferences("theme", MODE_PRIVATE);
        boolean isDark = sharedPreferencesTheme.getBoolean("isDark", false);
        SettingActivity.setDarkTheme(isDark);

        boolean loginStatus = sharedPreferences.getBoolean("loginStatus", false);
        if (loginStatus) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            finish();
        }

        buttonLogin.setOnClickListener(v -> {
            String nim = inputNim.getText().toString();
            String password = inputPassword.getText().toString();

            if (!nim.isEmpty() && !password.isEmpty()) {
                String userNim = sharedPreferences.getString("nim", "");
                String userPassword = sharedPreferences.getString("password", "");

                if (nim.equals(userNim) && password.equals(userPassword)) {
                    editor.putBoolean("loginStatus", true);
                    editor.apply();

                    Intent intent = new Intent(this, ProfileActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "NIM atau Password Salah", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Input tidak lengkap", Toast.LENGTH_SHORT).show();
            }
        });

        buttonRegister.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}