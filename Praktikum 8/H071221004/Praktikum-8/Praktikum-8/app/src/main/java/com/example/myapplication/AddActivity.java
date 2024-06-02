package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class AddActivity extends AppCompatActivity {
    // Deklarasi variabel UI dan database
    ImageButton ib_back;
    Button btn_submit;
    TextInputLayout textInputLayout_judul;
    TextInputLayout textInputLayout_deskripsi;
    DBConfig dbConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Memanggil metode onCreate dari superclass
        super.onCreate(savedInstanceState);
        // Mengaktifkan EdgeToEdge untuk tampilan layar penuh
        EdgeToEdge.enable(this);
        // Mengatur layout activity menggunakan layout activity_add.xml
        setContentView(R.layout.activity_add);
        // Menyetel padding untuk menghindari overlap dengan system bars
        // Menyetel listener untuk mendapatkan insets dari window (seperti status bar dan navigasi bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            // Mendapatkan insets dari system bars (status bar dan navigasi bar)
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            // Menyetel padding pada view agar tidak bertabrakan dengan system bars
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            // Mengembalikan insets agar dapat diterapkan oleh view
            return insets;
        });

        // Inisialisasi DBConfig
        dbConfig = new DBConfig(this);

        // Menghubungkan variabel dengan komponen UI di layout
        btn_submit = findViewById(R.id.btn_submit);
        textInputLayout_judul = findViewById(R.id.titleInput);
        textInputLayout_deskripsi = findViewById(R.id.descInput);
        ib_back = findViewById(R.id.btn_back);

        // Menambahkan event listener untuk tombol kembali
        ib_back.setOnClickListener(v -> showExitConfirmationDialog());

        // Menambahkan event listener untuk tombol submit
        btn_submit.setOnClickListener(v -> {
            // Mendapatkan teks dari input judul dan deskripsi
            String judul = textInputLayout_judul.getEditText().getText().toString().trim();
            String deskripsi = textInputLayout_deskripsi.getEditText().getText().toString().trim();

            // Validasi input
            if (judul.isEmpty()) {
                textInputLayout_judul.setError("Please enter your Judul");
            } else {
                // Memasukkan data ke database
                dbConfig.insertData(judul, deskripsi);
                // Menampilkan pesan sukses
                Toast.makeText(AddActivity.this, "Note added successfully", Toast.LENGTH_SHORT).show();
                // Mengakhiri activity
                finish();
            }
        });
    }

    // Menampilkan dialog konfirmasi ketika user ingin membatalkan
    // Metode untuk menampilkan dialog konfirmasi keluar
    private void showExitConfirmationDialog() {
        // Membuat instance AlertDialog.Builder dengan konteks activity ini
        new AlertDialog.Builder(this)
                // Mengatur judul dialog
                .setTitle("Cancel")
                // Mengatur pesan dialog
                .setMessage("Apakah anda ingin membatalkan penambahan pada form?")
                // Menambahkan tombol "No" yang menutup dialog saat diklik
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                // Menambahkan tombol "Yes" yang menutup dialog dan memanggil metode onBackPressed dari superclass saat diklik
                .setPositiveButton("Yes", (dialog, which) -> {
                    dialog.dismiss();
                    super.onBackPressed();
                })
                // Membuat dan menampilkan dialog
                .create()
                .show();
    }

    // Menimpa metode onBackPressed untuk menampilkan dialog konfirmasi sebelum kembali
    @Override
    public void onBackPressed() {
        // Memanggil metode onBackPressed dari superclass (untuk menjaga perilaku default back press)
        super.onBackPressed();
        // Menampilkan dialog konfirmasi keluar
        showExitConfirmationDialog();
    }
}


