package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.model.Post;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProfileActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tv_username, tv_name;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageView = findViewById(R.id.post_profile_image);
        tv_username = findViewById(R.id.account_username_tv);
        tv_name = findViewById(R.id.account_name_tv);
        progressBar = findViewById(R.id.progressBar);


        imageView.setVisibility(View.GONE);
        tv_name.setVisibility(View.GONE);
        tv_username.setVisibility(View.GONE);

        progressBar.setVisibility(View.VISIBLE);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);

                        imageView.setVisibility(View.VISIBLE);
                        tv_name.setVisibility(View.VISIBLE);
                        tv_username.setVisibility(View.VISIBLE);

                        Intent intent = getIntent();
                        Post post = intent.getParcelableExtra("post");

                        imageView.setImageResource(post.getProfileDrawableResourceId());
                        tv_name.setText(post.getName());
                        tv_username.setText(post.getUsername());
                    }
                });
            }
        });
    }
}