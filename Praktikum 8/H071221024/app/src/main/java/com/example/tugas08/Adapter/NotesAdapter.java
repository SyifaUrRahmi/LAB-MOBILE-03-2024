package com.example.tugas08.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas08.Activity.UpdateNoteActivity;
import com.example.tugas08.Model.Note;
import com.example.tugas08.R;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    ArrayList<Note> notes;
    Context context;

    public NotesAdapter(ArrayList<Note> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.setData(note);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView timestampTv, titleTv, descTv;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timestampTv = itemView.findViewById(R.id.timestamp_tv);
            titleTv = itemView.findViewById(R.id.title_tv);
            descTv = itemView.findViewById(R.id.desc_tv);
            this.view = itemView;
        }
        public void setData(Note note){
            timestampTv.setText(note.getTimestamp());
            titleTv.setText(note.getTitle());
            descTv.setText(note.getDesc());

            view.setOnClickListener(v -> {
                Intent intent = new Intent(context, UpdateNoteActivity.class);
                int idNote = note.getId();
                intent.putExtra("idNote", idNote);
                ((Activity) context).finish();
                context.startActivity(intent);
            });
        }
    }
}
