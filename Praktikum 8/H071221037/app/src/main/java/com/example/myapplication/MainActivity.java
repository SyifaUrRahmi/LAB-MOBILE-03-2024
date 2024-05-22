package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends ComponentActivity {

    ImageButton ib_delete, ib_search, ib_close;
    LinearLayout ll_noData;
    RecyclerView recyclerView;
    FloatingActionButton btnAdd;
    DatabaseHelper myDB;
    ArrayList<Book> bookList;
    ArrayList<Book> searchResultList;
    Adapter adapter;
    androidx.appcompat.widget.SearchView searchView; // Ubah tipe variabel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ll_noData = findViewById(R.id.noData);
        recyclerView = findViewById(R.id.rv_book);
        btnAdd = findViewById(R.id.btnAdd);
        ib_delete = findViewById(R.id.ib_Delete);
        ib_search = findViewById(R.id.ib_Search);
        ib_close = findViewById(R.id.ib_Close);
        searchView = findViewById(R.id.searchView);
        myDB = new DatabaseHelper(this);
        bookList = new ArrayList<>();
        searchResultList = new ArrayList<>();
        displayRecord();
        // Inisialisasi Adapter setelah pengambilan data
        adapter = new Adapter(this, bookList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        ib_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setVisibility(View.VISIBLE);
                ib_close.setVisibility(View.VISIBLE);
                ib_search.setVisibility(View.GONE);
                ib_delete.setVisibility(View.GONE);
            }
        });

        ib_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setQuery("", false);
                searchView.setVisibility(View.GONE);
                ib_search.setVisibility(View.VISIBLE);
                ib_close.setVisibility(View.GONE);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                displaySearchRecord(newText);
                return true;
            }
        });
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
            finish();
        });

        ib_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteConfirmation();
            }
        });
    }
    public void displayRecord() {
        Cursor cursor = myDB.getAllRecords();
        if (cursor.getCount() == 0) {
            ll_noData.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                String createdAt = cursor.getString(3);
                String updatedAt = cursor.getString(4);
                bookList.add(new Book(id, title, description, createdAt, updatedAt));
            }
            ll_noData.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
    public void displaySearchRecord(String keyword) {
        Cursor cursor = myDB.searchRecord(keyword);
        searchResultList.clear();
        if (cursor.getCount() == 0) {
            ll_noData.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                String createdAt = cursor.getString(3);
                String updatedAt = cursor.getString(4);
                searchResultList.add(new Book(id, title, description, createdAt, updatedAt));
            }
            ll_noData.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter.setData(searchResultList);
            adapter.notifyDataSetChanged();
        }
    }
    public void deleteConfirmation(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Apakah anda yakin ingin menghapus semua item ini?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHelper myDB = new DatabaseHelper(MainActivity.this);
                        myDB.deleteAllRecords();
                        Toast.makeText(MainActivity.this, "All Item Deleted", Toast.LENGTH_SHORT).show();
                        recreate();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Item not Deleted", Toast.LENGTH_SHORT).show();
                        // Batal menghapus
                        dialog.dismiss();
                    }
                });
        // Buat dan tampilkan dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
