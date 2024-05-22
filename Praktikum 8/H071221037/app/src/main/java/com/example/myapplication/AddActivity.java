package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.textfield.TextInputEditText;

public class AddActivity extends AppCompatActivity {

    private ImageButton ib_back;
    private TextInputEditText et_title, et_description;
    private Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Handling edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        et_title = findViewById(R.id.et_title);
        et_description = findViewById(R.id.et_description);
        btn_submit = findViewById(R.id.btnSubmit);
        ib_back = findViewById(R.id.ib_Back);

        // Set button click listeners
        btn_submit.setOnClickListener(v -> {
            if (et_title.getText().toString().isEmpty()) {
                et_title.setError("isi title terlebih dahulu");
            } else if (et_description.getText().toString().isEmpty()) {
                et_description.setError("Isi description terlebih dahulu");
            } else {
                DatabaseHelper myDB = new DatabaseHelper(AddActivity.this);
                myDB.insertRecord(et_title.getText().toString().trim(), et_description.getText().toString().trim());
                Log.d("AddActivity", "Title: " + et_title.getText().toString().trim());
                Log.d("AddActivity", "Description: " + et_description.getText().toString().trim());
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ib_back.setOnClickListener(v -> outConfirmation());

        // Adding the new onBackPressedDispatcher callback
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                outConfirmation();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    public void outConfirmation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
        builder.setMessage("Apakah anda ingin membatalkan Penambahan record?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(AddActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        // Buat dan tampilkan dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
