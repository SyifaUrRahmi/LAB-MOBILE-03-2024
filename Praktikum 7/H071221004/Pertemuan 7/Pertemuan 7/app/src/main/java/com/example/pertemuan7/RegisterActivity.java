package com.example.pertemuan7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.pertemuan7.R;

public class RegisterActivity extends AppCompatActivity { // Deklarasi kelas RegisterActivity yang merupakan subclass dari AppCompatActivity

    EditText et_nim, et_password; // Deklarasi dua EditText untuk input NIM dan password
    Button btn_register; // Deklarasi tombol untuk melakukan registrasi
    SharedPreferences sharedPreferences; // Deklarasi objek SharedPreferences untuk menyimpan dan mengakses data preferensi

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Metode onCreate dipanggil saat activity dibuat
        super.onCreate(savedInstanceState); // Memanggil metode onCreate dari superclass
        setContentView(R.layout.activity_register); // Mengatur layout activity_register sebagai tampilan activity ini

        et_nim = findViewById(R.id.et_nimRegister); // Menghubungkan EditText NIM dengan ID di layout
        et_password = findViewById(R.id.et_passwordResgister); // Menghubungkan EditText password dengan ID di layout
        btn_register = findViewById(R.id.btn_register2); // Menghubungkan tombol register dengan ID di layout

        btn_register.setOnClickListener(view -> { // Menetapkan listener untuk tombol register
            String nim = String.valueOf(et_nim.getText()); // Mengambil teks dari EditText NIM dan mengkonversi ke string
            String password = String.valueOf(et_password.getText()); // Mengambil teks dari EditText password dan mengkonversi ke string

            if (!nim.isEmpty() && !password.isEmpty()) { // Memeriksa apakah NIM dan password tidak kosong
                // Menghapus semua data registrasi yang ada
                sharedPreferences = getSharedPreferences("user_pref", MODE_PRIVATE); // Mendapatkan SharedPreferences dengan nama "user_pref"
                SharedPreferences.Editor editor = sharedPreferences.edit(); // Mendapatkan editor untuk mengubah SharedPreferences
                editor.clear(); // Menghapus semua data yang ada
                editor.apply(); // Menerapkan perubahan

                // Menyimpan data registrasi baru
                editor.putString("nim", nim); // Menyimpan NIM baru
                editor.putString("password", password); // Menyimpan password baru
                editor.apply(); // Menerapkan perubahan

                Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show(); // Menampilkan pesan sukses
                finish(); // Menutup RegisterActivity
            } else { // Jika NIM atau password kosong
                Toast.makeText(RegisterActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show(); // Menampilkan pesan kesalahan
            }
        });

//        sharedPreferences = getSharedPreferences("theme_pref", MODE_PRIVATE); // Mendapatkan SharedPreferences dengan nama "theme_pref"
//        boolean isDarkTheme = sharedPreferences.getBoolean("is_dark_theme", false); // Mengambil nilai tema gelap yang tersimpan, defaultnya false
//        if (isDarkTheme) { // Jika tema gelap diaktifkan
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); // Mengatur mode malam
//        } else { // Jika tema gelap tidak diaktifkan
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // Mengatur mode siang
//        }
    }
}
