package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomTabView;
    private HomeFragment homeFragment = new HomeFragment();
    private PostingFragment postingFragment = new PostingFragment();
    private SearchFragment searchFragment = new SearchFragment();
    private ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DB.init();
        setContentView(R.layout.activity_main);

        bottomTabView = findViewById(R.id.bottom_tab_view);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, homeFragment).commit();

        bottomTabView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home_tab) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, homeFragment).commit();
                    return true;
                } else if (item.getItemId() == R.id.posting_tab) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, postingFragment).commit();
                    return true;
                }
                else if (item.getItemId() == R.id.search_tab) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, searchFragment).commit();
                    return true;
                } else if (item.getItemId() == R.id.profile_tab) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, profileFragment).commit();
                    return true;
                }
                return false;
            }
        });
    }
}