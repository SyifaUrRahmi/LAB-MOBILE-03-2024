package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Deklarasi variabel UI dan database
    FloatingActionButton fabAdd;
    private RecyclerView rvSearch;
    private TextView tvNoData;
    private SearchView searchView;
    private DBConfig dbConfig;
    private List<Notes> notes;
    private NotesAdapter notesAdapter;

    // Metode onCreate, dipanggil saat activity dibuat
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Dipanggil ketika activity pertama kali dibuat
        super.onCreate(savedInstanceState);

        // Mengaktifkan fitur EdgeToEdge untuk mengoptimalkan penggunaan layar penuh
        EdgeToEdge.enable(this);

        // Menyetel layout XML yang digunakan oleh activity ini
        setContentView(R.layout.activity_main);

        // Menyetel padding untuk menghindari overlap dengan system bars (seperti status bar dan navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets; // Mengembalikan insets setelah mengatur padding
        });

        // Inisialisasi variabel UI dengan menghubungkannya ke elemen yang didefinisikan di XML
        fabAdd = findViewById(R.id.fab_add); // FloatingActionButton untuk menambahkan item baru
        rvSearch = findViewById(R.id.rv_search); // RecyclerView untuk menampilkan daftar item
        tvNoData = findViewById(R.id.tv_no_data); // TextView untuk menampilkan pesan jika tidak ada data
        searchView = findViewById(R.id.search); // SearchView untuk pencarian item

        // Inisialisasi DBConfig untuk mengakses database
        dbConfig = new DBConfig(this); // Membuat instance dari DBConfig

        // Inisialisasi list dan adapter untuk RecyclerView
        notes = new ArrayList<>(); // Membuat list kosong untuk menyimpan data item
        notesAdapter = new NotesAdapter(this, notes); // Membuat adapter untuk menghubungkan data dengan RecyclerView
        rvSearch.setAdapter(notesAdapter); // Menghubungkan adapter dengan RecyclerView
        rvSearch.setLayoutManager(new GridLayoutManager(this, 1)); // Menyetel layout manager untuk RecyclerView, menggunakan GridLayoutManager dengan 1 kolom


    // Memuat data dari database
        loadData("");

        // Set listener untuk FloatingActionButton untuk membuka AddActivity
        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });

        // Set listener untuk SearchView untuk menangani perubahan teks pencarian
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false; // Tidak melakukan apa-apa saat teks pencarian dikirim
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                loadData(newText); // Memuat data sesuai dengan teks pencarian
                return true;
            }
        });
    }

    // Metode untuk memuat data dari database
    private void loadData(String query) {
        notes.clear(); // Membersihkan list notes
        Cursor cursor;

        // Jika query kosong, ambil semua data, jika tidak cari berdasarkan judul
        if (query.isEmpty()) {
            cursor = dbConfig.getAllRecords();
        } else {
            cursor = dbConfig.searchByTitle(query);
        }

        // Jika cursor tidak kosong dan berhasil bergerak ke baris pertama
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Mendapatkan data dari setiap kolom
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(dbConfig.getColumnId()));
                String judul = cursor.getString(cursor.getColumnIndexOrThrow(dbConfig.getColumnJudul()));
                String deskripsi = cursor.getString(cursor.getColumnIndexOrThrow(dbConfig.getColumnDeskripsi()));
                String createdAt = cursor.getString(cursor.getColumnIndexOrThrow(dbConfig.getColumnCreatedAt()));
                String updatedAt = cursor.getString(cursor.getColumnIndexOrThrow(dbConfig.getColumnUpdatedAt()));
                // Menambahkan data ke list notes
                notes.add(new Notes(id, judul, deskripsi, createdAt, updatedAt));
            } while (cursor.moveToNext());
            cursor.close(); // Menutup cursor setelah selesai
        }

        // Mengatur visibilitas TextView dan RecyclerView berdasarkan data yang ditemukan
        if (notes.isEmpty()) {
            tvNoData.setVisibility(TextView.VISIBLE); // Menampilkan pesan "Tidak ada data"
            rvSearch.setVisibility(View.GONE); // Menyembunyikan RecyclerView
            Toast.makeText(this, "Tidak ada data yang ditemukan", Toast.LENGTH_SHORT).show(); // Menampilkan toast
        } else {
            tvNoData.setVisibility(View.GONE); // Menyembunyikan pesan "Tidak ada data"
            rvSearch.setVisibility(View.VISIBLE); // Menampilkan RecyclerView
        }

        // Memberitahu adapter bahwa data telah berubah
        notesAdapter.notifyDataSetChanged();
    }

    // Metode onResume, dipanggil saat activity dilanjutkan
    @Override
    protected void onResume() {
        super.onResume();
        loadData(""); // Memuat ulang data
    }

    // Metode onBackPressed untuk menangani tombol back
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity(); // Menutup semua activity di task ini
    }
}
