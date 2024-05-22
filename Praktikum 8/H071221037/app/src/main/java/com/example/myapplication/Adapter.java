package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    private ArrayList<Book> bookList;

    public Adapter(Context context, ArrayList<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.tv_title.setText(book.getTitle());
        holder.tv_description.setText(book.getDescription());
        if (book.getUpdatedAt() != null && !book.getUpdatedAt().isEmpty()) {
            holder.tv_createdAt.setText("Updated At: " + book.getUpdatedAt());
        } else {
            holder.tv_createdAt.setText("Created At: " + book.getCreatedAt());
        }
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public void setData(ArrayList<Book> searchResultList) {
        this.bookList = searchResultList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id, tv_title, tv_description, tv_createdAt;
        LinearLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_description = itemView.findViewById(R.id.tv_description);
            tv_createdAt = itemView.findViewById(R.id.tv_createdAt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Book clickedBook = bookList.get(position);
                        Intent intent = new Intent(context, UpdateActivity.class);
                        intent.putExtra("id", clickedBook.getId());
                        intent.putExtra("title", clickedBook.getTitle());
                        intent.putExtra("description", clickedBook.getDescription());
                        context.startActivity(intent);
                        ((Activity)context).finish();
                    }
                }
            });
        }
    }
}
