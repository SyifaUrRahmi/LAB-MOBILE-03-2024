package com.example.tuprak7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {
    private TextView textNim;
    private Button buttonLogout, buttonSetting;
    SharedPreferences sharedPreferences, sharedPreferencesTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textNim = findViewById(R.id.textNim);
        buttonLogout = findViewById(R.id.buttonLogout);
        buttonSetting = findViewById(R.id.buttonSetting);
        sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        sharedPreferencesTheme = getSharedPreferences("theme", MODE_PRIVATE);
        boolean isDark = sharedPreferencesTheme.getBoolean("isDark", false);
        SettingActivity.setDarkTheme(isDark);

        textNim.setText(sharedPreferences.getString("nim", ""));

        buttonLogout.setOnClickListener(v -> {
            editor.putBoolean("loginStatus", false);
            editor.apply();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        buttonSetting.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        });
    }
}