package com.example.tugas08.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.example.tugas08.Db.DBConfig;
import com.example.tugas08.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddNoteActivity extends AppCompatActivity {

    TextInputEditText titleTv, descTv;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Tambah");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        titleTv = findViewById(R.id.title_tv);
        descTv = findViewById(R.id.desc_tv);
        btnSubmit = findViewById(R.id.btn_submit);

        DBConfig db = new DBConfig(this);

        btnSubmit.setOnClickListener(v -> {
            if (titleTv.getText().toString().isEmpty()) {
                titleTv.setError("Judul harus diisi");
            } else if (descTv.getText().toString().isEmpty()) {
                descTv.setError("Deskripsi harus diisi");
            } else {
                long timestamp = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
                String formattedTimestamp = "Created at " + sdf.format(new Date(timestamp));

                db.insertNote(titleTv.getText().toString(), descTv.getText().toString(), formattedTimestamp);
                Intent intent = new Intent(this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        showConfirmationDialog();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            showConfirmationDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Batal");
        builder.setMessage("Apakah Anda ingin membatalkan penambahan pada form?");
        builder.setPositiveButton("Ya", (dialog, which) -> {
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);
        });
        builder.setNegativeButton("Tidak", (dialog, which) -> {

        });
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(dialogInterface -> {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.blue));
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.blue));
        });
        dialog.show();
    }
}