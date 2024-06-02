package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateActivity extends AppCompatActivity {
    // Deklarasi variabel untuk EditText dan Button
    private EditText et_UpdateJudul;
    private EditText et_UpdateDeskripsi;
    private Button btn_update;
    // Deklarasi variabel untuk DBConfig dan recordId
    private DBConfig dbConfig;
    private int recordId;

    // Metode onCreate, dipanggil saat activity dibuat
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Mengaktifkan fitur EdgeToEdge untuk mengoptimalkan penggunaan layar penuh
        EdgeToEdge.enable(this);

        // Menyetel layout XML yang digunakan oleh activity ini
        setContentView(R.layout.activity_update);

        // Menyetel padding untuk menghindari overlap dengan system bars (seperti status bar dan navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets; // Mengembalikan insets setelah mengatur padding
        });

        // Inisialisasi DBConfig untuk mengakses database
        dbConfig = new DBConfig(this);

        // Menghubungkan variabel UI dengan elemen yang didefinisikan di XML
        et_UpdateJudul = findViewById(R.id.et_upjudul); // EditText untuk judul
        et_UpdateDeskripsi = findViewById(R.id.et_updesc); // EditText untuk deskripsi
        btn_update = findViewById(R.id.btn_update); // Button untuk update

        // Mendapatkan Intent yang membawa data
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("record_id")) {
            // Mendapatkan recordId dari Intent
            recordId = intent.getIntExtra("record_id", -1);
            // Memuat data record berdasarkan ID
            loadRecordData(recordId);
        }

        // Set listener untuk tombol kembali
        findViewById(R.id.btn_backk).setOnClickListener(v -> {
            // Menampilkan dialog konfirmasi batal
            showCancelConfirmationDialog();
        });

        // Set listener untuk tombol hapus
        findViewById(R.id.btn_delete).setOnClickListener(v -> {
            // Menampilkan dialog konfirmasi hapus
            showDeleteConfirmationDialog();
        });

        // Set listener untuk tombol update
        btn_update.setOnClickListener(v -> {
            // Mendapatkan teks dari EditText
            String judul = et_UpdateJudul.getText().toString();
            String deskripsi = et_UpdateDeskripsi.getText().toString();

            // Validasi input
            if (!judul.isEmpty() && !deskripsi.isEmpty()) {
                // Update record di database
                dbConfig.updateRecord(recordId, judul, deskripsi);
                // Menutup activity
                finish();
            } else {
                // Menampilkan pesan error jika input kosong
                if (judul.isEmpty()) {
                    et_UpdateJudul.setError("Judul tidak boleh kosong");
                }
                if (deskripsi.isEmpty()) {
                    et_UpdateDeskripsi.setError("Deskripsi tidak boleh kosong");
                }
            }
        });
    }

    // Metode untuk memuat data record dari database
    private void loadRecordData(int id) {
        // Query untuk mendapatkan data record berdasarkan ID
        Cursor cursor = dbConfig.getReadableDatabase().rawQuery("SELECT * FROM " + dbConfig.getTableName() + " WHERE " + dbConfig.getColumnId() + " = ?", new String[]{String.valueOf(id)});
        if (cursor != null && cursor.moveToFirst()) {
            // Mengisi EditText dengan data dari cursor
            et_UpdateJudul.setText(cursor.getString(cursor.getColumnIndexOrThrow(dbConfig.getColumnJudul())));
            et_UpdateDeskripsi.setText(cursor.getString(cursor.getColumnIndexOrThrow(dbConfig.getColumnDeskripsi())));
            cursor.close(); // Menutup cursor setelah selesai
        }
    }

    // Metode untuk menampilkan dialog konfirmasi hapus
    private void showDeleteConfirmationDialog() {
        // Membuat AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus Note");
        builder.setMessage("Apakah anda yakin ingin menghapus item ini?");
        builder.setPositiveButton("Ya", (dialog, which) -> {
            // Hapus record dari database
            dbConfig.deleteRecords(recordId);
            // Menutup activity
            finish();
        });
        builder.setNegativeButton("Tidak", (dialog, which) -> dialog.dismiss()); // Menutup dialog
        builder.create().show(); // Menampilkan dialog
    }

    // Metode untuk menampilkan dialog konfirmasi batal
    private void showCancelConfirmationDialog() {
        // Membuat AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Batal");
        builder.setMessage("Apakah anda ingin membatalkan perubahan pada form?");
        builder.setPositiveButton("Ya", (dialog, which) -> {
            // Membuat Intent untuk kembali ke MainActivity
            Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Menutup activity saat ini
        });
        builder.setNegativeButton("Tidak", (dialog, which) -> {
            dialog.dismiss(); // Menutup dialog
            // Panggil finish() di sini setelah dialog ditutup
            finish();
        });
        builder.create().show(); // Menampilkan dialog
    }

    // Metode untuk menangani tombol kembali
    @Override
    public void onBackPressed() {
        // Memanggil super untuk fungsi dasar
        super.onBackPressed();
        // Menampilkan dialog konfirmasi batal
        showCancelConfirmationDialog();
    }
}
