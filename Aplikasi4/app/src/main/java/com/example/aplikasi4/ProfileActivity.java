package com.example.aplikasi4;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aplikasi4.Instagram;
import com.example.aplikasi4.R;
// Deklarasi kelas ProfileActivity yang merupakan turunan dari AppCompatActivity
public class ProfileActivity extends AppCompatActivity {

    // Override method onCreate yang dipanggil ketika aktivitas dibuat
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Memanggil metode onCreate dari kelas induk (AppCompatActivity)
        super.onCreate(savedInstanceState);
        // Menetapkan tata letak aktivitas menggunakan file xml activity_profile.xml
        setContentView(R.layout.activity_profile);

        // Mendapatkan referensi ImageView ivProfile dari tata letak
        ImageView ivProfile = findViewById(R.id.iv_profile);
        // Mendapatkan referensi TextView tvName dari tata letak
        TextView tvName = findViewById(R.id.tv_name);
        // Mendapatkan referensi TextView tvUsername dari tata letak
        TextView tvUsername = findViewById(R.id.tv_username);

        // Mendapatkan intent yang memulai aktivitas ini
        Intent intent = getIntent();
        // Mendapatkan objek Instagram yang dikirimkan melalui intent dengan kunci "instagram"
        Instagram instagram = intent.getParcelableExtra("instagram");

        // Menetapkan gambar profil dari objek Instagram ke ImageView ivProfile
        ivProfile.setImageResource(instagram.getFotoProfile());
        // Menetapkan nama dari objek Instagram ke TextView tvName
        tvName.setText(instagram.getName());
        // Menetapkan username dari objek Instagram ke TextView tvUsername
        tvUsername.setText(instagram.getUsername());
    }
}

