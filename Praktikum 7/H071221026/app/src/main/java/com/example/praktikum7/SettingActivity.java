package com.example.praktikum7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {
    TextView modeTextView;
    Switch modeSwitch;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        modeTextView = findViewById(R.id.TV_mode);
        modeSwitch = findViewById(R.id.switchBTN);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        boolean isDarkMode = sharedPreferences.getBoolean("isDarkMode", false);
        modeSwitch.setChecked(isDarkMode);
        updateModeText(isDarkMode);

        // Mengubah mode tampilan saat switch diubah
        modeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateModeText(isChecked);
                saveModeToPreferences(isChecked);
            }
        });
    }

    public void updateModeText(boolean isDarkMode) {
        if (isDarkMode) {
            modeTextView.setText("Dark Mode");
            modeTextView.setTextColor(Color.WHITE);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            modeTextView.setText("Light Mode");
            modeTextView.setTextColor(Color.BLACK);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public void saveModeToPreferences(boolean isDarkMode) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isDarkMode", isDarkMode);
        editor.apply();
    }
}