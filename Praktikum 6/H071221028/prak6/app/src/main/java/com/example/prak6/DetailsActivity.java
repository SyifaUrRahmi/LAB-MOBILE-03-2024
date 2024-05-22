package com.example.prak6;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.prak6.API.ApiService;
import com.example.prak6.API.GetUserResponse;
import com.example.prak6.API.RetrofitClient;
import com.example.prak6.API.User;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {

    private ImageView profileImageView;
    private TextView emailTextView;
    private  TextView usernameTextView;
    private ProgressBar detailsLoading;

    private boolean isLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);



        profileImageView = findViewById(R.id.details_profile_iv);
        usernameTextView = findViewById(R.id.details_username_tv);
        emailTextView = findViewById(R.id.details_email_tv);
        detailsLoading = findViewById(R.id.details_loading);
        isLoading = true;
        detailsLoading.setVisibility(View.VISIBLE);
        profileImageView.setVisibility(View.GONE);
        usernameTextView.setVisibility(View.GONE);
        emailTextView.setVisibility(View.GONE);


        int userId = getIntent().getIntExtra("USER_ID", -1);

        if(userId != -1){
            ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
            Call<GetUserResponse> call = apiService.getUser(userId);

            call.enqueue(new Callback<GetUserResponse>() {
                @Override
                public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                    if (response.isSuccessful()){
                        isLoading = false;
                        detailsLoading.setVisibility(View.GONE);
                        profileImageView.setVisibility(View.VISIBLE);
                        usernameTextView.setVisibility(View.VISIBLE);
                        emailTextView.setVisibility(View.VISIBLE);

                        User user = response.body().getUser();
                        usernameTextView.setText(user.getFirstName()+ " " + user.getLastName());
                        emailTextView.setText(user.getEmail());
                        Picasso.get().load(user.getAvatar()).into(profileImageView);

                    }
                }

                @Override
                public void onFailure(Call<GetUserResponse> call, Throwable t) {

                }
            });
        }
    }


}