package com.example.aplikasi4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.aplikasi4.fragment.HomeFragment;

// Deklarasi kelas MainActivity yang merupakan turunan dari AppCompatActivity
public class MainActivity extends AppCompatActivity {

    // Override method onCreate yang dipanggil ketika aktivitas dibuat
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Memanggil metode onCreate dari kelas induk (AppCompatActivity)
        super.onCreate(savedInstanceState);
        // Menetapkan tata letak aktivitas menggunakan file xml activity_main.xml
        setContentView(R.layout.activity_main);

        // Mendapatkan instance FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Membuat instance HomeFragment
        HomeFragment homeFragment = new HomeFragment();

        // Mencari fragment berdasarkan tag HomeFragment
        Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());

        // Memeriksa apakah fragment yang ditemukan bukan merupakan instance dari HomeFragment
        if (!(fragment instanceof HomeFragment)){
            // Jika bukan, maka tambahkan HomeFragment ke frame_container
            fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_container, homeFragment)
                    .commit();
        }
    }

    }
