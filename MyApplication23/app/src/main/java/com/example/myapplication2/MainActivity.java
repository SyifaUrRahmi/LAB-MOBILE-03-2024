package com.example.myapplication2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
//Deklarasi variabel-variabel untuk elemen-elemen UI seperti EditText, ImageView, dan Button.
    private EditText et1;
    private EditText et2;
    private ImageView image;
    private Button btn;
//Deklarasi variabel-variabel untuk menyimpan data yang diambil dari elemen UI.
    private String textFromEt1;
    private String textFromEt2;
    private Uri imageUri;
//Metode onCreate() yang dipanggil saat aktivitas dibuat. Di sini, layout ditetapkan, dan elemen-elemen UI diinisialisasi.
    @SuppressLint("MissingInflatedId")
    @Override
    //oncreate melakukan inisialisasi awal
    protected void onCreate(Bundle savedInstanceState) {
        //super memanggil metode onCreate() dari kelas induk
        super.onCreate(savedInstanceState);
        //Mengaktifkan Edge-to-Edge (perbatasan ke perbatasan) untuk aktivitas.
        //Ini adalah utilitas yang memastikan tampilan UI mengambil ruang yang tersedia penuh di layar.
        EdgeToEdge.enable(this);
        //Menetapkan layout aktivitas dari file XML activity_main.xml ke MainActivity.
        setContentView(R.layout.activity_main);
        //Menginisialisasi variabel-variabel untuk elemen UI berdasarkan ID yang sesuai dari layout XML.
        et1 = findViewById(R.id.nama);
        et2 = findViewById(R.id.uname);
        image = findViewById(R.id.getPict);
        btn = findViewById(R.id.btnSubmit);
        //Menambahkan onClickListener ke ImageView. Ketika ImageView diklik, akan membuka galeri untuk memilih gambar.
        image.setOnClickListener(view -> {
            Intent open = new Intent(Intent.ACTION_PICK);
            open.setType("image/*");
            launcherIntentGallery.launch(open);
        });
        //Menambahkan onClickListener ke Button. Ketika Button diklik,
        //akan memeriksa apakah field-field sudah diisi dengan benar dan gambar sudah dipilih sebelum memproses data.
        btn.setOnClickListener(view -> {
            //trim untuk menghapus spasi diawal dan akhir text
            textFromEt1 = et1.getText().toString().trim();
            textFromEt2 = et2.getText().toString().trim();
            if (textFromEt1.isEmpty() || textFromEt2.isEmpty()) {
                Toast.makeText(this, "Isi semua field terlebih dahulu", Toast.LENGTH_SHORT).show();
            } else if (imageUri == null) {
                Toast.makeText(this, "Pilih gambar terlebih dahulu", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, MainActivity2.class);
                intent.putExtra("textFromEt1", textFromEt1);
                intent.putExtra("textFromEt2", textFromEt2);
                intent.putExtra("imageUri", imageUri.toString());
                startActivity(intent);
            }
        });
    }
    //Mendeklarasikan launcher untuk membuka galeri gambar. Ketika gambar dipilih, gambar akan ditampilkan di ImageView.
    ActivityResultLauncher<Intent> launcherIntentGallery = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        imageUri = data.getData();
                        image.setImageURI(imageUri);
                    }
                }
            });
    //Metode-metode getter untuk mendapatkan nilai dari variabel-variabel yang menyimpan data dari elemen UI.
    public String getTextFromEt1() {
        return textFromEt1;
    }

    public String getTextFromEt2() {
        return textFromEt2;
    }

    public Uri getImageUri() {
        return imageUri;
    }
}