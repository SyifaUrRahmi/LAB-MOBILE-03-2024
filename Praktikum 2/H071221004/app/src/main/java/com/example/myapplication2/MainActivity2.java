package com.example.myapplication2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    //Deklarasi variabel untuk elemen-elemen UI seperti EditText
    // dan Button yang akan digunakan dalam aktivitas MainActivity2.
    private EditText et3;
    private EditText et4;
    private Button btn1;
    //Deklarasi variabel untuk menyimpan data yang akan dimasukkan oleh pengguna melalui EditText et3 dan et4.
    private String textFromEt3;
    private String textFromEt4;
    //@SuppressLint("MissingInflatedId") digunakan untuk memberi petunjuk kepada lint checker
    // Android Studio agar menangguhkan pemeriksaan tertentuterkait ID yang hilang
    // atau tidak sesuai dengan tipe elemen yang diharapkan dalam layout XML
    @SuppressLint("MissingInflatedId")
    @Override
    //Metode onCreate() yang dipanggil saat aktivitas MainActivity2 dibuat atau diinisialisasi.
    //Di dalam metode ini, layout ditetapkan, elemen-elemen UI diinisialisasi, dan event listeners ditambahkan.
    protected void onCreate(Bundle savedInstanceState) {
        //Memanggil metode onCreate() dari kelas induk (AppCompatActivity)
        super.onCreate(savedInstanceState);
        //mengaktifkan Edge-to-Edge untuk aktivitas ini, dan menetapkan layout aktivitas dari file XML activity_main2.xml.
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        //Menginisialisasi variabel-variabel untuk elemen-elemen UI berdasarkan ID yang sesuai dari layout XML.
        et3 = findViewById(R.id.title);
        et4 = findViewById(R.id.content);
        btn1 = findViewById(R.id.btnSave);
        //// Memeriksa apakah field-field sudah diisi dengan benar sebelum memproses data.
        btn1.setOnClickListener(view -> {
            textFromEt3 = et3.getText().toString().trim();
            textFromEt4 = et4.getText().toString().trim();

            if (textFromEt3.isEmpty() || textFromEt4.isEmpty()) {
                Toast.makeText(this, "Isi semua field terlebih dahulu", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, MainActivity3.class);
                // Meneruskan data ke MainActivity3
                intent.putExtra("textFromEt1", getIntent().getStringExtra("textFromEt1"));
                intent.putExtra("textFromEt2", getIntent().getStringExtra("textFromEt2"));
                intent.putExtra("textFromEt3", textFromEt3);
                intent.putExtra("textFromEt4", textFromEt4);
                // Meneruskan data gambar dari MainActivity
                intent.putExtra("imageUri", getIntent().getStringExtra("imageUri"));
                startActivity(intent);
            }
        });
    }
    //getTextFromEt3() dipanggil dari luar kelas (misalnya dari kelas lain),
    // metode ini akan mengembalikan nilai dari variabel textFromEt3.
    public String getTextFromEt3() {
        return textFromEt3;
    }

    public String getTextFromEt4() {
        return textFromEt4;
    }
}