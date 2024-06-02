package com.example.aplikasi4;


import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

// Deklarasi kelas Instagram yang meng-implementasikan Parcelable
public class Instagram implements Parcelable {

    // Deklarasi variabel instance untuk data Instagram
    private String username; // Variabel untuk menyimpan username
    private String name; // Variabel untuk menyimpan nama
    private String desc; // Variabel untuk menyimpan deskripsi
    private int fotoProfile; // Variabel untuk menyimpan ID foto profil
    private int fotoPostingan; // Variabel untuk menyimpan ID foto postingan
    private Uri selectedImageUri; // Variabel untuk menyimpan URI gambar yang dipilih



    // Constructor dengan parameter untuk membuat objek Instagram dengan nilai awal
    public Instagram(String username, String name, String desc, int fotoProfile, int fotoPostingan) {
        // Menginisialisasi variabel username dengan nilai dari parameter username
        this.username = username;
        // Menginisialisasi variabel name dengan nilai dari parameter name
        this.name = name;
        // Menginisialisasi variabel desc dengan nilai dari parameter desc
        this.desc = desc;
        // Menginisialisasi variabel fotoProfile dengan nilai dari parameter fotoProfile
        this.fotoProfile = fotoProfile;
        // Menginisialisasi variabel fotoPostingan dengan nilai dari parameter fotoPostingan
        this.fotoPostingan = fotoPostingan;
    }


    // Constructor dengan parameter untuk membuat objek Instagram dengan nilai awal
    public Instagram(String username, String tia, String konten, int tia1, Uri selectedImageUri) {
        // Menginisialisasi variabel username dengan nilai dari parameter username
        this.username = username;
        // Menginisialisasi variabel name dengan nilai dari parameter bowo (nama pengguna?)
        this.name = tia;
        // Menginisialisasi variabel desc dengan nilai dari parameter konten (deskripsi?)
        this.desc = konten;
        // Menginisialisasi variabel fotoProfile dengan nilai dari parameter bowo1
        this.fotoProfile = tia1;
        // Menginisialisasi variabel selectedImageUri dengan nilai dari parameter selectedImageUri
        this.selectedImageUri = selectedImageUri;
    }


    // Metode getter untuk mendapatkan nilai dari variabel username
    public String getUsername() {
        return username;
    }

    // Metode setter untuk mengatur nilai variabel username
    public void setUsername(String username) {
        this.username = username;
    }

    // Metode getter untuk mendapatkan nilai dari variabel name
    public String getName() {
        return name;
    }

    // Metode setter untuk mengatur nilai variabel name
    public void setName(String name) {
        this.name = name;
    }

    // Metode getter untuk mendapatkan nilai dari variabel desc
    public String getDesc() {
        return desc;
    }

    // Metode setter untuk mengatur nilai variabel desc
    public void setDesc(String desc) {
        this.desc = desc;
    }

    // Metode getter untuk mendapatkan nilai dari variabel fotoProfile
    public int getFotoProfile() {
        return fotoProfile;
    }

    // Metode setter untuk mengatur nilai variabel fotoProfile
    public void setFotoProfile(int fotoProfile) {
        this.fotoProfile = fotoProfile;
    }

    // Metode getter untuk mendapatkan nilai dari variabel fotoPostingan
    public int getFotoPostingan() {
        return fotoPostingan;
    }

    // Metode setter untuk mengatur nilai variabel fotoPostingan
    public void setFotoPostingan(int fotoPostingan) {
        this.fotoPostingan = fotoPostingan;
    }

    // Metode getter untuk mendapatkan nilai dari variabel selectedImageUri
    public Uri getSelectedImageUri() {
        return selectedImageUri;
    }

    // Metode setter untuk mengatur nilai variabel selectedImageUri
    public void setSelectedImageUri(Uri selectedImageUri) {
        this.selectedImageUri = selectedImageUri;
    }

    // Constructor Parcelable untuk membuat objek Instagram dari Parcel
    protected Instagram(Parcel in) {
        // Mengambil nilai dari Parcel dan menetapkannya ke variabel username
        username = in.readString();
        // Mengambil nilai dari Parcel dan menetapkannya ke variabel name
        name = in.readString();
        // Mengambil nilai dari Parcel dan menetapkannya ke variabel desc
        desc = in.readString();
        // Mengambil nilai dari Parcel dan menetapkannya ke variabel fotoProfile
        fotoProfile = in.readInt();
        // Mengambil nilai dari Parcel dan menetapkannya ke variabel fotoPostingan
        fotoPostingan = in.readInt();
    }


    // Deklarasi objek Creator untuk membuat objek Parcelable dari Parcel
    public static final Creator<Instagram> CREATOR = new Creator<Instagram>() {
        // Override method createFromParcel untuk membuat objek Parcelable dari Parcel
        @Override
        public Instagram createFromParcel(Parcel in) {
            // Mengembalikan objek Instagram baru yang dibuat dari Parcel
            return new Instagram(in);
        }

        // Override method newArray untuk membuat array objek Parcelable
        @Override
        public Instagram[] newArray(int size) {
            // Mengembalikan array objek Instagram yang baru dengan ukuran sesuai yang diberikan
            return new Instagram[size];
        }
    };


    // Override method describeContents untuk mendeskripsikan isi dari objek Parcelable
    @Override
    public int describeContents() {
        // Mengembalikan nilai 0 karena tidak ada spesifikasi tambahan untuk mendeskripsikan isi objek Parcelable
        return 0;
    }

    // Override method writeToParcel untuk menulis data objek ke Parcel
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        // Menulis nilai username ke Parcel
        dest.writeString(username);
        // Menulis nilai name ke Parcel
        dest.writeString(name);
        // Menulis nilai desc ke Parcel
        dest.writeString(desc);
        // Menulis nilai fotoProfile ke Parcel
        dest.writeInt(fotoProfile);
        // Menulis nilai fotoPostingan ke Parcel
        dest.writeInt(fotoPostingan);
    }

}

