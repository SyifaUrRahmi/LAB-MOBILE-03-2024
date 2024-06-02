package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DBConfig extends SQLiteOpenHelper {

    // Mendeklarasikan nama database, versi database, dan nama tabel beserta kolom-kolomnya
    private static final String DATABASE_NAME = "database-8";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "pertemuan_8";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_JUDUL = "judul";
    private static final String COLUMN_DESKRIPSI = "deskripsi";
    private static final String COLUMN_CREATED_AT = "created_at";
    private static final String COLUMN_UPDATED_AT = "updated_at";

    // Konstruktor untuk DBConfig, memanggil super untuk SQLiteOpenHelper
    public DBConfig(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Metode ini dipanggil saat database pertama kali dibuat
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Membuat tabel dengan kolom-kolom yang dideklarasikan sebelumnya
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_JUDUL + " TEXT,"
                + COLUMN_DESKRIPSI + " TEXT,"
                + COLUMN_CREATED_AT + " TEXT,"
                + COLUMN_UPDATED_AT + " TEXT)");
    }

    // Metode untuk memasukkan data ke dalam tabel
    public void insertData(String judul, String deskripsi) {
        SQLiteDatabase db = getWritableDatabase();  // Mendapatkan database yang dapat ditulis
        ContentValues values = new ContentValues(); // Membuat objek ContentValues untuk menyimpan data
        values.put(COLUMN_JUDUL, judul);            // Menambahkan judul ke ContentValues
        values.put(COLUMN_DESKRIPSI, deskripsi);    // Menambahkan deskripsi ke ContentValues
        String currentTime = getCurrentDateTime();  // Mendapatkan waktu saat ini
        values.put(COLUMN_CREATED_AT, currentTime); // Menambahkan waktu pembuatan ke ContentValues
        values.put(COLUMN_UPDATED_AT, currentTime); // Menambahkan waktu pembaruan ke ContentValues
        db.insert(TABLE_NAME, null, values);        // Memasukkan data ke dalam tabel
        db.close();                                 // Menutup database
    }

    // Metode untuk mengambil semua data dari tabel
    public Cursor getAllRecords() {
        SQLiteDatabase db = getReadableDatabase();  // Mendapatkan database yang dapat dibaca
        return db.query(TABLE_NAME, null, null, null, null, null, null); // Mengambil semua data dari tabel
    }

    // Metode untuk mencari data berdasarkan judul
    public Cursor searchByTitle(String judul) {
        SQLiteDatabase db = getReadableDatabase();  // Mendapatkan database yang dapat dibaca
        // Melakukan query pencarian berdasarkan judul
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_JUDUL + " LIKE ?", new String[]{"%" + judul + "%"});
    }

    // Metode untuk memperbarui data berdasarkan id
    public void updateRecord(int id, String judul, String deskripsi) {
        SQLiteDatabase db = getWritableDatabase();  // Mendapatkan database yang dapat ditulis
        ContentValues values = new ContentValues(); // Membuat objek ContentValues untuk menyimpan data yang diperbarui
        values.put(COLUMN_JUDUL, judul);            // Menambahkan judul ke ContentValues
        values.put(COLUMN_DESKRIPSI, deskripsi);    // Menambahkan deskripsi ke ContentValues
        values.put(COLUMN_UPDATED_AT, getCurrentDateTime()); // Menambahkan waktu pembaruan ke ContentValues
        db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}); // Memperbarui data di tabel
    }

    // Metode untuk menghapus data berdasarkan id
    public void deleteRecords(int id) {
        SQLiteDatabase db = getWritableDatabase();  // Mendapatkan database yang dapat ditulis
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}); // Menghapus data dari tabel
    }

    // Metode ini dipanggil saat database perlu di-upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Saat ini metode ini tidak melakukan apa-apa, tetapi bisa digunakan untuk mengubah struktur tabel jika diperlukan di versi mendatang
    }

    // Metode untuk mendapatkan waktu saat ini dalam format "yyyy-MM-dd HH:mm:ss"
    private String getCurrentDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    // Metode getter untuk mendapatkan nama tabel
    public String getTableName() {
        return TABLE_NAME;
    }

    // Metode getter untuk mendapatkan nama kolom id
    public String getColumnId() {
        return COLUMN_ID;
    }

    // Metode getter untuk mendapatkan nama kolom judul
    public String getColumnJudul() {
        return COLUMN_JUDUL;
    }

    // Metode getter untuk mendapatkan nama kolom deskripsi
    public String getColumnDeskripsi() {
        return COLUMN_DESKRIPSI;
    }

    // Metode getter untuk mendapatkan nama kolom waktu pembuatan
    public String getColumnCreatedAt() {
        return COLUMN_CREATED_AT;
    }

    // Metode getter untuk mendapatkan nama kolom waktu pembaruan
    public String getColumnUpdatedAt() {
        return COLUMN_UPDATED_AT;
    }
}
