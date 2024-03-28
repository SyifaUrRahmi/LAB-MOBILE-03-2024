package com.example.tugas2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class MainActivity3 extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewUsername;
    private ImageView imageView;
    private TextView textviewTitle;
    private TextView textviewContent;

    Datas datas;
    Notes notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

//        Bundle bundle = getIntent().getExtras();
//        Datas datas= bundle.getParcelable("datas");
//        Notes notes= bundle.getParcelable("notes");

        textViewName = findViewById(R.id.outputNama);
        textViewUsername = findViewById(R.id.outputUsername);
        imageView = findViewById(R.id.fotoAkhir);
        textviewTitle = findViewById(R.id.outputTitle);
        textviewContent = findViewById(R.id.outputKonten);

        datas = getIntent().getParcelableExtra("datas");
        notes = getIntent().getParcelableExtra("notes");

        textViewName.setText(datas.getName());
        textViewUsername.setText(datas.getUsername());
        Glide.with(this).load(datas.getImageBytes()).into(imageView);
        textviewTitle.setText(notes.getTitel());
        textviewContent.setText(notes.getKonten());
    }
}