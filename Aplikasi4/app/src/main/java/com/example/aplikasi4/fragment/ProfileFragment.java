package com.example.aplikasi4.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.aplikasi4.R;

// Deklarasi kelas ProfileFragment yang meng-extends Fragment
public class ProfileFragment extends Fragment {
    // Override method onCreateView untuk mengatur tampilan Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Meng-inflate layout XML fragment_profile ke dalam tampilan (View) Fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    // Override method onViewCreated yang dipanggil setelah tampilan Fragment selesai dibuat
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Memanggil implementasi method onViewCreated dari superclass
        super.onViewCreated(view, savedInstanceState);

        // Menginisialisasi ImageView IV_Home dengan ID IV_Home dari layout view
        ImageView IV_Home = view.findViewById(R.id.IV_Home);
        // Menginisialisasi ImageView IV_Postingan dengan ID IV_Post dari layout view
        ImageView IV_Postingan = view.findViewById(R.id.IV_Post);

        // Mengatur OnClickListener untuk ImageView IV_Home
        IV_Home.setOnClickListener(v -> {
            // Membuat instance baru dari HomeFragment
            HomeFragment homeFragment = new HomeFragment();
            // Mendapatkan FragmentManager dari parent Fragment atau Activity
            FragmentManager fragmentManager = getParentFragmentManager();
            // Memulai transaksi Fragment untuk mengganti tampilan dalam frame_container dengan HomeFragment yang baru
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, homeFragment)
                    // Menambahkan transaksi ke back stack untuk manajemen navigasi
                    .addToBackStack(null)
                    // Melakukan commit untuk menyelesaikan transaksi
                    .commit();
        });

        // Mengatur OnClickListener untuk ImageView IV_Postingan
        IV_Postingan.setOnClickListener(v -> {
            // Membuat instance baru dari PostinganFragment
            PostinganFragment postinganFragment = new PostinganFragment();
            // Mendapatkan FragmentManager dari parent Fragment atau Activity
            FragmentManager fragmentManager = getParentFragmentManager();
            // Memulai transaksi Fragment untuk mengganti tampilan dalam frame_container dengan PostinganFragment yang baru
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, postinganFragment)
                    // Menambahkan transaksi ke back stack untuk manajemen navigasi
                    .addToBackStack(null)
                    // Melakukan commit untuk menyelesaikan transaksi
                    .commit();
        });

    }
}