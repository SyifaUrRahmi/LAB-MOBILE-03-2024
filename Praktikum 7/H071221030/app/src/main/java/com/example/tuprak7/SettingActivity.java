package com.example.tuprak7;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettingActivity extends AppCompatActivity {
    private Switch darkModeToggle;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        darkModeToggle = findViewById(R.id.darkModeToggle);
        sharedPreferences = getSharedPreferences("theme", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        boolean isDark = sharedPreferences.getBoolean("isDark", false);
        darkModeToggle.setChecked(isDark);

        darkModeToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            setDarkTheme(isChecked);
            editor.putBoolean("isDark", isChecked);
            editor.apply();
        });
    }


    public static void setDarkTheme(boolean isDark) {
        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}