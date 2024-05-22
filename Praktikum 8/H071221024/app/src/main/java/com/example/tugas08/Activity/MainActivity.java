package com.example.tugas08.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tugas08.Adapter.NotesAdapter;
import com.example.tugas08.Db.DBConfig;
import com.example.tugas08.Model.Note;
import com.example.tugas08.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    RecyclerView notesRv;
    ArrayList<Note> notes = new ArrayList<>();
    Context context = this;
    NestedScrollView nestedScrollView;
    RelativeLayout noData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBConfig db = new DBConfig(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Notes");
        }

        nestedScrollView = findViewById(R.id.nestedScroll);
        noData = findViewById(R.id.noData);

        SearchView searchView = findViewById(R.id.searchBtn);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Note> detailNotes = new ArrayList<>();
                Cursor cursor = db.getNotes(newText);
                if(cursor.moveToFirst()){
                    do{
                        int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                        String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                        String desc = cursor.getString(cursor.getColumnIndexOrThrow("desc"));
                        String timestamp = cursor.getString(cursor.getColumnIndexOrThrow("timestamp"));
                        Note note = new Note(id, title, desc, timestamp);
                        detailNotes.add(note);
                    } while(cursor.moveToNext());
                    NotesAdapter adapter = new NotesAdapter(detailNotes, context);
                    noData.setVisibility(View.GONE);
                    nestedScrollView.setVisibility(View.VISIBLE);
                    notesRv.setAdapter(adapter);
                } else {
                    nestedScrollView.setVisibility(View.GONE);
                    noData.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

        btnAdd = findViewById(R.id.btn_add);
        notesRv = findViewById(R.id.rv_notes);

        btnAdd.setOnClickListener(v -> {
            Intent intent =  new Intent(this, AddNoteActivity.class);
            finish();
            startActivity(intent);
        });

        Cursor cursor = db.getAllNotes();
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String desc = cursor.getString(cursor.getColumnIndexOrThrow("desc"));
                String timestamp = cursor.getString(cursor.getColumnIndexOrThrow("timestamp"));
                Note note = new Note(id, title, desc, timestamp);
                notes.add(note);
            } while(cursor.moveToNext());
            NotesAdapter adapter = new NotesAdapter(notes, this);
            noData.setVisibility(View.GONE);
            nestedScrollView.setVisibility(View.VISIBLE);
            notesRv.setAdapter(adapter);
        } else {
            nestedScrollView.setVisibility(View.GONE);
            noData.setVisibility(View.VISIBLE);
        }
    }
}