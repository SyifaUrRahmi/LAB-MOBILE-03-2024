package com.example.myapplication2;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {
    //Deklarasi variabel untuk elemen-elemen UI seperti TextView dan
    // ImageView yang akan digunakan dalam aktivitas MainActivity3.
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private ImageView image;
    //Metode onCreate() yang dipanggil saat aktivitas MainActivity3 dibuat atau diinisialisasi. Di dalam metode ini,
    //layout ditetapkan, elemen-elemen UI diinisialisasi, dan data yang diterima dari aktivitas sebelumnya ditampilkan.
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        textView1 = findViewById(R.id.textNama);
        textView2 = findViewById(R.id.textUname);
        textView3 = findViewById(R.id.textTitle);
        textView4 = findViewById(R.id.textContent);

        image = findViewById(R.id.imageView);

        String text1 = getIntent().getStringExtra("textFromEt1");
        String text2 = getIntent().getStringExtra("textFromEt2");
        String text3 = getIntent().getStringExtra("textFromEt3");
        String text4 = getIntent().getStringExtra("textFromEt4");
        String imageUriString = getIntent().getStringExtra("imageUri");


        textView1.setText(text1);
        textView2.setText(text2);
        textView3.setText(text3);
        textView4.setText(text4);
        //Uri.parse(imageUriString) digunakan untuk mengonversi string URI ke objek
        //URI agar bisa digunakan dalam menetapkan gambar ke ImageView menggunakan image.setImageURI(imageUri)
        Uri imageUri = Uri.parse(imageUriString);
        image.setImageURI(imageUri);
        //enampilkan data yang diterima dari aktivitas sebelumnya ke dalam
        // elemen-elemen UI yang sesuai di layout activity_main3.xml.
    }
}