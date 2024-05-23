package com.example.pertemuan8;

import android.content.DialogInterface;
import android.content.Intent;
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

    ImageButton ib_back;
    Button btn_submit;
    TextInputLayout textInputLayout_judul;
    TextInputLayout textInputLayout_deskripsi;
    DBConfig dbConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbConfig = new DBConfig(this);

        btn_submit = findViewById(R.id.btn_submit);
        textInputLayout_judul = findViewById(R.id.titleInput);
        textInputLayout_deskripsi = findViewById(R.id.descInput);

        ib_back = findViewById(R.id.btn_back);

        ib_back.setOnClickListener(v ->
            new AlertDialog.Builder(this)
                    .setTitle("Cancel")
                    .setMessage("Apakah anda ingin membatalkan penambahan pada form?")
                    .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                    .setPositiveButton("Yes", (dialog, which) -> { finish(); })
                    .create()
                    .show()
        );

        btn_submit.setOnClickListener(v -> {
            String judul = textInputLayout_judul.getEditText().getText().toString().trim();
            String deskripsi = textInputLayout_deskripsi.getEditText().getText().toString().trim();

            if (judul.isEmpty() ) {
                textInputLayout_judul.setError("Please enter your Judul");
            } else {
                dbConfig.insertData(judul, deskripsi);
                Toast.makeText(AddActivity.this, "Note added successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
