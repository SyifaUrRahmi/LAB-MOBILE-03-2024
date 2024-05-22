package com.example.pertemuan7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity { // Deklarasi kelas MainActivity yang merupakan subclass dari AppCompatActivity

    Button btn_login, btn_register; // Deklarasi dua tombol, satu untuk login dan satu untuk register
    EditText et_nim, et_password; // Deklarasi dua EditText untuk input NIM dan password
    SharedPreferences sharedPreferences; // Deklarasi objek SharedPreferences untuk menyimpan dan mengakses data preferensi

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Metode onCreate dipanggil saat activity dibuat
        super.onCreate(savedInstanceState); // Memanggil metode onCreate dari superclass
        setContentView(R.layout.activity_main); // Mengatur layout activity_main sebagai tampilan activity ini

        et_nim = findViewById(R.id.et_nim); // Menghubungkan EditText NIM dengan ID di layout
        et_password = findViewById(R.id.et_password); // Menghubungkan EditText password dengan ID di layout
        btn_login = findViewById(R.id.btn_login); // Menghubungkan tombol login dengan ID di layout
        btn_register = findViewById(R.id.btn_register); // Menghubungkan tombol register dengan ID di layout

        // Memeriksa status login saat aplikasi dibuka
        checkLoginStatus(); // Memanggil metode untuk memeriksa status login

        btn_register.setOnClickListener(view -> { // Menetapkan listener untuk tombol register
            // Buka RegisterActivity jika tombol register diklik
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class); // Membuat intent untuk berpindah ke RegisterActivity
            startActivity(intent); // Memulai activity RegisterActivity
        });

        btn_login.setOnClickListener(view -> { // Menetapkan listener untuk tombol login
            // Mendapatkan nilai NIM dan password dari EditText
            String nim = et_nim.getText().toString().trim(); // Mengambil teks dari EditText NIM dan menghilangkan spasi di awal/akhir
            String password = et_password.getText().toString().trim(); // Mengambil teks dari EditText password dan menghilangkan spasi di awal/akhir

            // Memeriksa apakah input tidak kosong dan valid
            if (!nim.isEmpty() && !password.isEmpty()) { // Memeriksa apakah NIM dan password tidak kosong
                // Melakukan validasi login
                boolean isValid = isValidLogin(nim, password); // Memanggil metode validasi login
                if (isValid) { // Jika validasi berhasil
                    // Jika login berhasil, simpan status login dan arahkan ke LoginActivity
                    saveLoginStatus(true); // Memanggil metode untuk menyimpan status login dengan parameter true
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class); // Membuat intent untuk berpindah ke LoginActivity
                    startActivity(intent); // Memulai activity LoginActivity
                    finish(); // Selesaikan MainActivity
                } else { // Jika validasi gagal
                    Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show(); // Menampilkan pesan kesalahan
                }
            } else { // Jika NIM atau password kosong
                Toast.makeText(MainActivity.this, "Please enter NIM and password", Toast.LENGTH_SHORT).show(); // Menampilkan pesan kesalahan
            }
        });

        // Pengaturan tema berdasarkan preferensi yang tersimpan
        sharedPreferences = getSharedPreferences("theme_pref", MODE_PRIVATE); // Mendapatkan SharedPreferences dengan nama "theme_pref"
        boolean isDarkTheme = sharedPreferences.getBoolean("is_dark_theme", false); // Mengambil nilai tema gelap yang tersimpan, defaultnya false
        if (isDarkTheme) { // Jika tema gelap diaktifkan
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); // Mengatur mode malam
        } else { // Jika tema gelap tidak diaktifkan
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // Mengatur mode siang
        }
    }

    // Method untuk memeriksa status login
    private void checkLoginStatus() { // Metode untuk memeriksa status login
        sharedPreferences = this.getSharedPreferences("user_pref", MODE_PRIVATE); // Mendapatkan SharedPreferences dengan nama "user_pref"
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false); // Mengambil nilai status login yang tersimpan, defaultnya false
        if (isLoggedIn) { // Jika pengguna sudah login
            // Jika pengguna sudah `login, arahkan ke LoginActivity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class); // Membuat intent untuk berpindah ke LoginActivity
            startActivity(intent); // Memulai activity LoginActivity
            finish(); // Selesaikan MainActivity
        }
    }

    // Method untuk validasi login
    private boolean isValidLogin(String nim, String password) { // Metode untuk validasi login
        // Di sini Anda dapat melakukan validasi login dengan memeriksa NIM dan password
        SharedPreferences sharedPreferences = getSharedPreferences("user_pref", MODE_PRIVATE); // Mendapatkan SharedPreferences dengan nama "user_pref"
        String storedPassword = sharedPreferences.getString("password", ""); // Mengambil password yang tersimpan
        String storedNim = sharedPreferences.getString("nim", ""); // Mengambil NIM yang tersimpan

        return storedNim.equals(nim) && storedPassword.equals(password); // Membandingkan NIM dan password yang diinput dengan yang tersimpan
    }

    // Method untuk menyimpan status login ke SharedPreferences
    private void saveLoginStatus(boolean isLoggedIn) { // Metode untuk menyimpan status login
        SharedPreferences sharedPreferences = getSharedPreferences("user_pref", MODE_PRIVATE); // Mendapatkan SharedPreferences dengan nama "user_pref"
        SharedPreferences.Editor editor = sharedPreferences.edit(); // Mendapatkan editor untuk mengubah SharedPreferences
        editor.putBoolean("isLoggedIn", isLoggedIn); // Menyimpan status login
        editor.apply(); // Menerapkan perubahan
    }
}
