package com.example.tugas2;

import android.os.Parcel;
import android.os.Parcelable;

public class Notes implements Parcelable {

    private String konten;
    private String titel;

    public Notes(String konten, String titel) {
        this.konten = konten;
        this.titel = titel;
    }

    protected Notes(Parcel in) {
        konten = in.readString();
        titel = in.readString();
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(konten);
        dest.writeString(titel);
    }
}

