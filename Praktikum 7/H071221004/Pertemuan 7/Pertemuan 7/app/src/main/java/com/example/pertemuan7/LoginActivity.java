package com.example.pertemuan7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class LoginActivity extends AppCompatActivity { // Deklarasi kelas LoginActivity yang merupakan subclass dari AppCompatActivity

    TextView tv_welcome; // Deklarasi TextView untuk menampilkan pesan selamat datang
    Button btn_logout, btn_setting; // Deklarasi dua tombol, satu untuk logout dan satu untuk membuka pengaturan
    SharedPreferences sharedPreferences; // Deklarasi objek SharedPreferences untuk menyimpan dan mengakses data preferensi

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Metode onCreate dipanggil saat activity dibuat
        super.onCreate(savedInstanceState); // Memanggil metode onCreate dari superclass
        setContentView(R.layout.activity_login); // Mengatur layout activity_login sebagai tampilan activity ini

        tv_welcome = findViewById(R.id.tv_welcome); // Menghubungkan TextView dengan ID di layout
        btn_logout = findViewById(R.id.btn_logout); // Menghubungkan tombol logout dengan ID di layout
        btn_setting = findViewById(R.id.btn_setting); // Menghubungkan tombol pengaturan dengan ID di layout

//        kode ini bertujuan untuk mendapatkan nilai NIM (Nomor Induk Mahasiswa)
//        yang disimpan dalam SharedPreferences dengan nama "user_pref". Jika data tersebut tidak ditemukan,
//        maka akan mengembalikan nilai default berupa string kosong.
        sharedPreferences = getSharedPreferences("user_pref", MODE_PRIVATE); // Mendapatkan SharedPreferences dengan nama "user_pref"
        String nim = sharedPreferences.getString("nim", ""); // Mengambil nilai NIM yang tersimpan, defaultnya kosong

        // Menetapkan teks selamat datang dengan nim yang login
        tv_welcome.setText("Selamat Datang " + nim); // Mengatur teks tv_welcome dengan pesan selamat datang dan NIM

        btn_logout.setOnClickListener(view -> { // Menetapkan listener untuk tombol logout
            // Menghapus status login (logout) dari SharedPreferences
            saveLoginStatus(false); // Memanggil metode saveLoginStatus dengan parameter false

            // Mengarahkan kembali ke MainActivity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class); // Membuat intent untuk berpindah ke MainActivity
            startActivity(intent); // Memulai activity MainActivity
            finish(); // Menutup LoginActivity saat ini
        });

        btn_setting.setOnClickListener(new View.OnClickListener() { // Menetapkan listener untuk tombol pengaturan
            @Override
            public void onClick(View v) { // Implementasi metode onClick
                Intent intent = new Intent(LoginActivity.this, SettingActivity.class); // Membuat intent untuk berpindah ke SettingActivity
                startActivity(intent); // Memulai activity SettingActivity
            }
        });

//        // Pengaturan tema berdasarkan preferensi yang tersimpan
//        sharedPreferences = getSharedPreferences("theme_pref", MODE_PRIVATE); // Mendapatkan SharedPreferences dengan nama "theme_pref"
//        boolean isDarkTheme = sharedPreferences.getBoolean("is_dark_theme", false); // Mengambil nilai tema gelap yang tersimpan, defaultnya false
//        if (isDarkTheme) { // Jika tema gelap diaktifkan
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); // Mengatur mode malam
//        } else { // Jika tema gelap tidak diaktifkan
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // Mengatur mode siang
//        }
    }

    // Method untuk menyimpan status login ke SharedPreferences
    private void saveLoginStatus(boolean isLoggedIn) { // Metode untuk menyimpan status login
        SharedPreferences sharedPreferences = getSharedPreferences("user_pref", MODE_PRIVATE); // Mendapatkan SharedPreferences dengan nama "user_pref"
        SharedPreferences.Editor editor = sharedPreferences.edit(); // Mendapatkan editor untuk mengubah SharedPreferences
        editor.putBoolean("isLoggedIn", isLoggedIn); // Menyimpan status login
        editor.apply(); // Menerapkan perubahan
    }
}
