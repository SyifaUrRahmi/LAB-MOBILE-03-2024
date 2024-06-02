package com.example.aplikasi4.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.aplikasi4.DataSource;
import com.example.aplikasi4.Instagram;
import com.example.aplikasi4.PostinganAdapter;
import com.example.aplikasi4.R;
import com.example.aplikasi4.MainActivity;

public class HomeFragment extends Fragment {

//    konstanta EXTRA_INSTAGRAM yang digunakan untuk menyimpan kunci data tambahan pada intent
    public static final String EXTRA_INSTAGRAM = "extra_instagram";

//     Objek LayoutInflater digunakan untuk meng-inflate (mengisi)
//     layout XML menjadi tampilan (View) yang dapat ditampilkan dalam UI.
//     ViewGroup yang berfungsi sebagai wadah untuk tampilan yang akan
//     diinflate. Dalam konteks Fragment, container ini adalah tampilan
//     parent yang akan menampung tampilan Fragment.
//     Bundle yang menyimpan data tentang status instance sebelumnya
//     dari Fragment, yang dapat digunakan untuk memulihkan state Fragment jika diperlukan.
    @Override
//    menginisialisasi tata letak (layout) Fragment dengan mengembalikan tata letak yang diinflate dari file XML fragment_home.xml
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    //melakukan inisialisasi tampilan dan berbagai operasi setelah tampilan Fragment telah dibuat dan ditampilkan.
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //memastikan bahwa implementasi default dari onViewCreated dari superclass dijalankan sebelum kode tambahan dijalankan
        super.onViewCreated(view, savedInstanceState);

        //cara untuk mengambil referensi RecyclerView dari tampilan yang telah diinflate sebelumnya
        RecyclerView rvPostingan = view.findViewById(R.id.rv_post);
        rvPostingan.setHasFixedSize(true);
        //diatur untuk RecyclerView dan diisi dengan data dari DataSource.instagrams.
        PostinganAdapter postinganAdapter = new PostinganAdapter(DataSource.instagrams);
        rvPostingan.setAdapter(postinganAdapter);

        // menerima data dari intent menggunakan Bundle yang dikirim melalui argument Fragment
        Bundle bundle = getArguments();
        if (bundle != null) {
            Instagram instagram = bundle.getParcelable(EXTRA_INSTAGRAM);
            if (instagram != null) {
                // kemudian menambahkan data Instagram ke DataSource
                DataSource.instagrams.add(0, instagram);
                // instagrams dan memberitahu adapter bahwa data telah berubah
                postinganAdapter.notifyDataSetChanged();
            }
        }
        //menginisialisasi dua variabel iv_posting dan iv_profile sebagai objek ImageView
        ImageView iv_posting = view.findViewById(R.id.IV_Post);
        ImageView iv_profile = view.findViewById(R.id.IV_Profile);

        // Mengatur OnClickListener untuk ImageView iv_posting
        iv_posting.setOnClickListener(v -> {
            // Membuat instance baru dari PostinganFragment
            PostinganFragment postinganFragment = new PostinganFragment();
            // Mendapatkan FragmentManager dari parent Fragment atau Activity
            FragmentManager fragmentManager = getParentFragmentManager();
            // Memulai transaksi Fragment
            fragmentManager
                    // Mengganti tampilan dalam frame_container dengan postinganFragment yang baru
                    .beginTransaction()
                    .replace(R.id.frame_container, postinganFragment)
                    // Menambahkan transaksi ke back stack untuk manajemen navigasi
                    // agar dapat kembali ke Fragment sebelumnya saat tombol kembali ditekan
                    .addToBackStack(null)
                    // Melakukan commit untuk menyelesaikan transaksi
                    //transaksi Fragment yang telah dibuat akan dieksekusi
                    .commit();
        });

        // Mengatur OnClickListener untuk ImageView iv_profile
        iv_profile.setOnClickListener(v -> {
            // Membuat instance baru dari ProfileFragment
            ProfileFragment profileFragment = new ProfileFragment();
            // Mendapatkan FragmentManager dari parent Fragment atau Activity
            FragmentManager fragmentManager = getParentFragmentManager();
            // Memulai transaksi Fragment
            fragmentManager
                    // Mengganti tampilan dalam frame_container dengan profileFragment yang baru
                    .beginTransaction()
                    .replace(R.id.frame_container, profileFragment)
                    // Menambahkan transaksi ke back stack untuk manajemen navigasi
                    .addToBackStack(null)
                    // Melakukan commit untuk menyelesaikan transaksi
                    .commit();
        });

    }
}
