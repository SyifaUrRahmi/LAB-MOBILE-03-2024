package com.example.aplikasi4;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasi4.fragment.ProfileFragment;

import java.util.ArrayList;

// Deklarasi kelas PostinganAdapter yang merupakan turunan dari RecyclerView.Adapter<PostinganAdapter.ViewHolder>
public class PostinganAdapter extends RecyclerView.Adapter<PostinganAdapter.ViewHolder> {

    // Variabel untuk menyimpan daftar Instagram
    private ArrayList<Instagram> instagrams;

    // Constructor untuk PostinganAdapter dengan parameter daftar Instagram
    public PostinganAdapter(ArrayList<Instagram> instagrams) {
        this.instagrams = instagrams; // Inisialisasi daftar Instagram dengan parameter yang diberikan
    }

    // Override method onCreateViewHolder untuk membuat ViewHolder baru
    @NonNull
    @Override
    public PostinganAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Meng-inflate layout postingan.xml ke dalam View
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postingan, parent, false);
        // Mengembalikan ViewHolder baru dengan View yang telah di-inflate
        return new PostinganAdapter.ViewHolder(view);
    }

    // Override method onBindViewHolder untuk mengikat data ke ViewHolder
    @Override
    public void onBindViewHolder(@NonNull PostinganAdapter.ViewHolder holder, int position) {
        // Mendapatkan objek Instagram dari daftar Instagram berdasarkan posisi
        Instagram instagram1 = instagrams.get(position);

        // Menetapkan nilai username dari objek Instagram ke TextView tv_username di ViewHolder
        holder.tv_username.setText(instagram1.getUsername());
        // Menetapkan nilai name dari objek Instagram ke TextView tv_name di ViewHolder
        holder.tv_name.setText(instagram1.getName());
        // Menetapkan gambar profil dari objek Instagram ke ImageView ivProfile di ViewHolder
        holder.ivProfile.setImageResource(instagram1.getFotoProfile());
        // Menetapkan gambar postingan dari objek Instagram ke ImageView ivFeed di ViewHolder
        holder.ivFeed.setImageResource(instagram1.getFotoPostingan());
        // Menetapkan URI gambar yang dipilih dari objek Instagram ke ImageView IVFeed di ViewHolder
        holder.IVFeed.setImageURI(instagram1.getSelectedImageUri());
        // Menetapkan nilai deskripsi dari objek Instagram ke TextView tv_caption di ViewHolder
        holder.tv_caption.setText(instagram1.getDesc());


        // Set listener onClickListener untuk ImageView ivProfile di ViewHolder
        holder.ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat objek Intent untuk memulai ProfileActivity
                Intent intent = new Intent(holder.context, ProfileActivity.class);
                // Menambahkan data objek Instagram ke intent dengan kunci "instagram"
                intent.putExtra("instagram", instagram1);
                // Memulai aktivitas dengan intent yang telah disiapkan
                holder.context.startActivity(intent);
            }

    });

        // Set listener onClickListener untuk TextView tv_name di ViewHolder
        holder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat objek Intent untuk memulai ProfileActivity
                Intent intent = new Intent(holder.context, ProfileActivity.class);
                // Menambahkan data objek Instagram ke intent dengan kunci "instagram"
                intent.putExtra("instagram", instagram1);
                // Memulai aktivitas dengan intent yang telah disiapkan
                holder.context.startActivity(intent);
            }
        });

        // Set listener onClickListener untuk TextView tv_username di ViewHolder
        holder.tv_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat objek Intent untuk memulai ProfileActivity
                Intent intent = new Intent(holder.context, ProfileActivity.class);
                // Menambahkan data objek Instagram ke intent dengan kunci "instagram"
                intent.putExtra("instagram", instagram1);
                // Memulai aktivitas dengan intent yang telah disiapkan
                holder.context.startActivity(intent);
            }
        });

        // Set listener onClickListener untuk ImageView ivDelete di ViewHolder
        holder.ivDelete.setOnClickListener(v -> {
            // Membuat objek AlertDialog.Builder untuk menampilkan dialog konfirmasi
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.context);
            // Menetapkan judul dialog
            builder.setTitle("Konfirmasi");
            // Menetapkan pesan dialog
            builder.setMessage("Apakah Anda yakin ingin menghapus postingan ini?");
            // Menambahkan tombol positif ("Ya") ke dialog dengan listener onClickListener
            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Mendapatkan posisi adapter dari ViewHolder
                    int adapterPosition = holder.getAdapterPosition();
                    // Memeriksa apakah posisi adapter valid
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        // Menghapus item dari daftar Instagram berdasarkan posisi adapter
                        instagrams.remove(adapterPosition);
                        // Memberitahu adapter bahwa item telah dihapus dari posisi tersebut
                        notifyItemRemoved(adapterPosition);
                    }
                }
            });

            // Menambahkan tombol negatif ("Tidak") ke dialog dengan listener onClickListener
            builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Menutup dialog saat tombol "Tidak" ditekan
                    dialog.dismiss();
                }
            });
// Membuat dan menampilkan dialog yang telah dibuat
            builder.create().show();
});

        }

    // Override method getItemCount untuk mengembalikan jumlah item dalam RecyclerView
    @Override
    public int getItemCount() {
        // Mengembalikan jumlah item dalam daftar Instagram
        return instagrams.size();
    }


    // Deklarasi kelas ViewHolder yang merupakan turunan dari RecyclerView.ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {

        // Deklarasi elemen-elemen UI yang akan ditampilkan dalam ViewHolder
        ImageView ivFeed, ivProfile, ivDelete, IVFeed;
        TextView tv_caption, tv_username, tv_name;
        Context context;

        // Constructor ViewHolder untuk inisialisasi elemen-elemen UI
        public ViewHolder(@NonNull View itemView) {
            // Memanggil constructor dari kelas induk (RecyclerView.ViewHolder) dengan itemView sebagai parameter
            super(itemView);

            // Menginisialisasi elemen-elemen UI berdasarkan ID yang diberikan dalam file layout item postingan
            ivFeed = itemView.findViewById(R.id.IV_Postingan);
            IVFeed = itemView.findViewById(R.id.IV_Postingan); // Duplikasi ID, sebaiknya diganti dengan ID yang berbeda
            ivProfile = itemView.findViewById(R.id.IV_Profile);
            ivDelete = itemView.findViewById(R.id.IV_Delete);
            tv_caption = itemView.findViewById(R.id.TV_Desc);
            tv_username = itemView.findViewById(R.id.TV_username);
            tv_name = itemView.findViewById(R.id.TV_name);

            // Mendapatkan context dari itemView untuk digunakan dalam aktivitas seperti memulai intent
            context = itemView.getContext();
        }
    }

}
