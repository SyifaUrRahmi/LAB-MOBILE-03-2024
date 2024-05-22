package com.example.prak7iya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {


    TextView change_tv;
    Switch switch_button;

    SharedPreferences sharedPreferences;
    public static final String SHARED_PREFS = "sharedPref";
    public static final String DARK_MODE = "darkMode";
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        change_tv = findViewById(R.id.change_tv);
        switch_button = findViewById(R.id.switch_button);
        sharedPreferences = getSharedPreferences("ThemePrefs",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        boolean isDarkTheme = sharedPreferences.getBoolean("isDarkTheme",false);
        switch_button.setChecked(isDarkTheme);

        switch_button.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                setDarkTheme();
            } else {
                setLightTheme();
            }
            editor.putBoolean("is_dark_theme", isChecked);
            editor.apply();
        });


    }
    private void setDarkTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    private void setLightTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}