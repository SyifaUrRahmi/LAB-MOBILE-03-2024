package com.example.tugas2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextContent;

    private Button tombolSave;
    Intent intent;
    Datas datas;

    Notes notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editTextTitle = findViewById(R.id.judul);
        editTextContent = findViewById(R.id.konten);
        tombolSave = findViewById(R.id.tombolSave);

        tombolSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity2.this, MainActivity3.class);
                String title = editTextTitle.getText().toString();
                String content = editTextContent.getText().toString();

                notes = new Notes(content, title);
                datas = getIntent().getParcelableExtra("datas");
                intent.putExtra("notes", notes);
                intent.putExtra("datas", datas);
                startActivity(intent);
//                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
//
//                Intent intent3 = new Intent(MainActivity2.this, MainActivity3.class);
//                startActivity(intent3);
            }
        });
    }
}