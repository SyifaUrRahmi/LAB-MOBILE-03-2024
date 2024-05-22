package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

public class UpdateActivity extends AppCompatActivity {
    private ImageButton ib_back, ib_delete;
    private TextInputEditText et_title, et_description;
    private Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // Handling edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        et_title = findViewById(R.id.et_title2);
        et_description = findViewById(R.id.et_description2);
        btn_update = findViewById(R.id.btnUpdate);
        ib_back = findViewById(R.id.ib_Back2);
        ib_delete = findViewById(R.id.ib_Delete2);

        // Retrieve data from intent
        String id = getIntent().getStringExtra("id");
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");

        et_title.setText(title);
        et_description.setText(description);

        // Set button click listeners
        btn_update.setOnClickListener(v -> updateData(id));
        ib_back.setOnClickListener(v -> outConfirmation());
        ib_delete.setOnClickListener(v -> deleteConfirmation(id));

        // Adding the new onBackPressedDispatcher callback
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                outConfirmation();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    public void updateData(String id) {
        String updatedTitle = et_title.getText().toString();
        String updatedDescription = et_description.getText().toString();
        DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
        myDB.updateRecord(id, updatedTitle, updatedDescription);
        Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void outConfirmation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
        builder.setMessage("Apakah anda ingin membatalkan update pada data?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
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

    public void deleteConfirmation(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
        builder.setMessage("Apakah anda yakin ingin menghapus item ini?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
                    myDB.deleteRecord(id);
                    Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        // Buat dan tampilkan dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
