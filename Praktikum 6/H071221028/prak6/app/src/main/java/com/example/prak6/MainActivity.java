package com.example.prak6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.prak6.API.ApiService;
import com.example.prak6.API.RetrofitClient;
import com.example.prak6.API.User;
import com.example.prak6.API.GetUserListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements  UserListAdapter.OnUserItemClickListener{
    private UserListAdapter userListAdapter;
    private RecyclerView userListRecyclerView;
    private ProgressBar loadingIndicator;
    private boolean isLoading = false;

    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.loadingIndicator = findViewById(R.id.loading_indicator);

        this.userListRecyclerView = findViewById(R.id.user_list_rv);
        this.userListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.userListAdapter = new UserListAdapter(this);
        this.userListRecyclerView.setAdapter(this.userListAdapter);

        loadUserList(currentPage); // load the initial data (page = 1)
    }

    private void loadUserList(int page) {
        this.isLoading = true;
        this.loadingIndicator.setVisibility(View.VISIBLE);
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        Call<GetUserListResponse> call = apiService.getUsers(page);

        call.enqueue(new Callback<GetUserListResponse>() {
            @Override
            public void onResponse(Call<GetUserListResponse> call, Response<GetUserListResponse> response) {
                if (response.isSuccessful()) {
                    List<User> userList = response.body().getUsers();
                    userListAdapter.addUsers(userList);
                    isLoading = false;
                    loadingIndicator.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<GetUserListResponse> call, Throwable t) {
                isLoading = false;
                loadingIndicator.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Data failed to load!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onLoadMoreButtonClick(View view) {
        currentPage++;
        loadUserList(currentPage);
    }

    @Override
    public void onUserItemClick(User user) {
        Intent intent =  new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra("USER_ID", user.getId());
        startActivity(intent);
    }
}