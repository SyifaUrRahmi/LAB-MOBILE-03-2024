package com.example.tugas2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;

public class MainActivity<Drawable> extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PICK_DRIVE_REQUEST = 2;
    private ImageButton imageButtonAwal;
    private EditText editTextName;
    private EditText editTextUsername;

    private Button tombolSubmit;
    Intent intent;


    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButtonAwal = findViewById(R.id.fotoAwal);
        editTextName = findViewById(R.id.InputName);
        editTextUsername = findViewById(R.id.UsernameName);

        imageButtonAwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageSourceDialog();
            }
        });


        tombolSubmit = findViewById(R.id.tombolAwal);

        tombolSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, MainActivity2.class);
                // Mendapatkan bitmap dari ImageButton
                Drawable drawable = (Drawable) imageButtonAwal.getDrawable();
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

                // Mengubah bitmap menjadi byte array
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();

                // Mendapatkan data dari EditText
                String name = editTextName.getText().toString();
                String username = editTextUsername.getText().toString();

                // Membuat objek Data
                Datas datas = new Datas(name, username, imageBytes);

                if (datas.getImageBytes() == null){
                    Toast.makeText(MainActivity.this, "please pick a photo first", Toast.LENGTH_SHORT).show();
                    return;
                }else if (name.isEmpty() && datas.getImageBytes() != null){
                    editTextName.setError("Isi Nama Terlebih Dahulu");
                    return;
                }else if(username.isEmpty() && datas.getImageBytes() != null){
                    editTextUsername.setError("Username Harus Diisi Dulu");
                    return;
                }
                // Menyimpan objek Data ke dalam Bundle
                intent.putExtra("datas", datas);
                startActivity(intent);

//                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
//
//                Intent intent2 = new Intent(MainActivity.this, MainActivity2.class);
//                startActivity(intent2);
            }
        });
    }

    private void showImageSourceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pilih Gambar").setItems(new String[]{"Galeri", "Google Drive"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    openGallery();
                }else if (which == 1){
                    openGoogleDrive();
                }
            }
        });
        builder.show();
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void openGoogleDrive() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, PICK_DRIVE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                Uri imageUri = data.getData();
                imageButtonAwal.setImageURI(imageUri);
            }
        } else if (requestCode == PICK_DRIVE_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                Uri fileUri = data.getData();
                // Handle Google Drive file here
                // You may need to use Google Drive API to get the actual image content
            }
        }
    }
}