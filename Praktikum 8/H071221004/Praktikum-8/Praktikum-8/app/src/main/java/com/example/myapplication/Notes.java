package com.example.myapplication;

import android.os.Parcelable;

public class Notes {

    // Deklarasi variabel instance untuk menyimpan data catatan
    private int id;
    private String judul;
    private String deskripsi;
    private String createdAt;
    private String updatedAt;

    // Konstruktor untuk menginisialisasi objek Notes dengan nilai-nilai tertentu
    public Notes(int id, String judul, String deskripsi, String createdAt, String updatedAt) {
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getter untuk mendapatkan nilai id
    public int getId() {
        return id;
    }

    // Setter untuk menetapkan nilai id
    public void setId(int id) {
        this.id = id;
    }

    // Getter untuk mendapatkan nilai judul
    public String getJudul() {
        return judul;
    }

    // Setter untuk menetapkan nilai judul
    public void setJudul(String judul) {
        this.judul = judul;
    }

    // Getter untuk mendapatkan nilai deskripsi
    public String getDeskripsi() {
        return deskripsi;
    }

    // Setter untuk menetapkan nilai deskripsi
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    // Getter untuk mendapatkan nilai createdAt
    public String getCreatedAt() {
        return createdAt;
    }

    // Setter untuk menetapkan nilai createdAt
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    // Getter untuk mendapatkan nilai updatedAt
    public String getUpdatedAt() {
        return updatedAt;
    }

    // Setter untuk menetapkan nilai updatedAt
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
