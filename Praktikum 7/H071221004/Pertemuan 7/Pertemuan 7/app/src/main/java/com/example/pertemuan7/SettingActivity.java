package com.example.pertemuan7;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingActivity extends AppCompatActivity { // Deklarasi kelas SettingActivity yang merupakan subclass dari AppCompatActivity

    Switch sw_tema; // Deklarasi objek Switch untuk mengatur tema
    SharedPreferences sharedPreferences; // Deklarasi objek SharedPreferences untuk menyimpan dan mengakses data preferensi
    public static final String SHARED_PREFS = "sharedPrefs"; // Deklarasi konstanta nama SharedPreferences
    public static final String DARK_MODE = "darkMode"; // Deklarasi konstanta untuk kunci mode gelap
    private SharedPreferences.Editor editor; // Deklarasi objek editor untuk mengedit SharedPreferences

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Metode onCreate dipanggil saat activity dibuat
        super.onCreate(savedInstanceState); // Memanggil metode onCreate dari superclass
        setContentView(R.layout.activity_setting); // Mengatur layout activity_setting sebagai tampilan activity ini

        sw_tema = findViewById(R.id.sw_tema); // Menghubungkan Switch tema dengan ID di layout
        sharedPreferences = getSharedPreferences("theme_pref", MODE_PRIVATE); // Mendapatkan SharedPreferences dengan nama "theme_pref"
        editor = sharedPreferences.edit(); // Mendapatkan editor untuk mengubah SharedPreferences

        boolean isDarkTheme = sharedPreferences.getBoolean("is_dark_theme", false); // Mengambil nilai tema gelap yang tersimpan, defaultnya false
        sw_tema.setChecked(isDarkTheme); // Mengatur status Switch sesuai dengan nilai yang tersimpan

        sw_tema.setOnCheckedChangeListener((buttonView, isChecked) -> { // Menetapkan listener untuk perubahan status Switch
            // Memeriksa apakah Switch diaktifkan
            if (isChecked) { // Jika Switch diaktifkan
                setDarkTheme(); // Memanggil metode untuk mengatur tema gelap
            } else { // Jika Switch tidak diaktifkan
                setLightTheme(); // Memanggil metode untuk mengatur tema terang
            }
            // Menyimpan status tema yang dipilih
            editor.putBoolean("is_dark_theme", isChecked); // Menyimpan nilai tema gelap yang baru
            editor.apply(); // Menerapkan perubahan
        });
    }

    private void setDarkTheme() { // Metode untuk mengatur tema aplikasi ke tema gelap
        // Mengatur tema aplikasi ke tema gelap
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); // Mengatur mode malam
    }

    private void setLightTheme() { // Metode untuk mengatur tema aplikasi ke tema terang
        // Mengatur tema aplikasi ke tema terang
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // Mengatur mode siang
    }
}
