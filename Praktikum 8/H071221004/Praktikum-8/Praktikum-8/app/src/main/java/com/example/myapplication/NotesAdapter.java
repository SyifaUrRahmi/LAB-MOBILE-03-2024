package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    // Deklarasi variabel instance
    private Context context;
    private List<Notes> notesList;

    // Konstruktor untuk menginisialisasi adapter dengan konteks dan daftar catatan
    public NotesAdapter(Context context, List<Notes> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    // Metode untuk membuat ViewHolder baru dan meng-inflate layout item
    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout item dari file XML
        View view = LayoutInflater.from(context).inflate(R.layout.search, parent, false);
        // Mengembalikan ViewHolder baru yang berisi view yang di-inflate
        return new ViewHolder(view);
    }

    // Metode untuk mengikat data ke ViewHolder
    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        // Mendapatkan item catatan berdasarkan posisi
        Notes notes = notesList.get(position);
        // Mengatur teks judul pada TextView
        holder.tvTitle.setText(notes.getJudul());
        // Mengatur teks deskripsi pada TextView
        holder.tvDesc.setText(notes.getDeskripsi());

        // Mengatur teks timestamp berdasarkan apakah catatan telah diperbarui atau tidak
        String timestampText;
        if (notes.getCreatedAt().equals(notes.getUpdatedAt())) {
            timestampText = "Created at " + notes.getCreatedAt();
        } else {
            timestampText = "Updated at " + notes.getUpdatedAt();
        }
        holder.tvTimestamp.setText(timestampText);

        // Menambahkan listener untuk itemView yang akan membuka UpdateActivity saat diklik
        holder.itemView.setOnClickListener(v -> {
            // Membuat Intent untuk membuka UpdateActivity
            Intent intent = new Intent(context, UpdateActivity.class);
            // Menambahkan ID catatan ke Intent sebagai extra
            intent.putExtra("record_id", notes.getId());
            // Memulai aktivitas dengan konteks
            context.startActivity(intent);
        });
    }

    // Metode untuk mendapatkan jumlah item dalam daftar
    @Override
    public int getItemCount() {
        return notesList.size();
    }

    // Kelas ViewHolder untuk menahan referensi view item
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Deklarasi variabel untuk TextView
        TextView tvTitle, tvDesc, tvTimestamp;

        // Konstruktor ViewHolder untuk menginisialisasi variabel view
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Menghubungkan variabel dengan elemen di layout item
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvTimestamp = itemView.findViewById(R.id.tv_timestamp);
        }
    }
}
