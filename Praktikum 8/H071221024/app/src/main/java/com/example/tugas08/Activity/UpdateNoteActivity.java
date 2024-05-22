package com.example.tugas08.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.tugas08.Db.DBConfig;
import com.example.tugas08.Model.Note;
import com.example.tugas08.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UpdateNoteActivity extends AppCompatActivity {

    TextInputEditText titleTv, descTv;
    Button btnDelete, btnUpdate;
    Note note;
    String title, desc, timestamp;
    int id;

    DBConfig db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Ubah");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(R.layout.action_bar_delete_button);

            View actionBarView = actionBar.getCustomView();
            btnDelete = actionBarView.findViewById(R.id.btn_delete);

            titleTv = findViewById(R.id.title_tv);
            descTv = findViewById(R.id.desc_tv);
            btnUpdate = findViewById(R.id.btn_update);

            btnDelete.setOnClickListener( v -> {
                showConfirmationDeleteDialog();
            });

            int userId = getIntent().getIntExtra("idNote", 0);

            db = new DBConfig(this);
            Cursor cursor = db.getNoteById(userId);
            if(cursor.moveToFirst()){
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                desc = cursor.getString(cursor.getColumnIndexOrThrow("desc"));
                timestamp = cursor.getString(cursor.getColumnIndexOrThrow("timestamp"));
                note = new Note(id, title, desc, timestamp);
            }
            titleTv.setText(title);
            descTv.setText(desc);

            btnUpdate.setOnClickListener(v -> {
                if (titleTv.getText().toString().isEmpty()) {
                    titleTv.setError("Judul harus diisi");
                } else if (descTv.getText().toString().isEmpty()) {
                    descTv.setError("Deskripsi harus diisi");
                } else {
                    long timestamp = System.currentTimeMillis();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
                    String formattedTimestamp = "Updated at " + sdf.format(new Date(timestamp));

                    db.updateNote(id, titleTv.getText().toString(), descTv.getText().toString(), formattedTimestamp);
                    Intent intent = new Intent(this, MainActivity.class);
                    finish();
                    startActivity(intent);
                }
            });
        }
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

    private void showConfirmationDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus Note");
        builder.setMessage("Apakah Anda yakin ingin menghapus item ini?");
        builder.setPositiveButton("Ya", (dialog, which) -> {
            db.deleteNote(id);
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