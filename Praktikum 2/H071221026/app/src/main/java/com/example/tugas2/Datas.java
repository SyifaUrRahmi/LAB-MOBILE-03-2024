package com.example.tugas2;

import android.os.Parcel;
import android.os.Parcelable;

public class Datas implements Parcelable {

    private String name;
    private String username;
    private byte[] imageBytes;

    public Datas(String name, String username, byte[] imageBytes) {
        this.name = name;
        this.username = username;
        this.imageBytes = imageBytes;
    }

    protected Datas(Parcel in) {
        name = in.readString();
        username = in.readString();
        imageBytes = in.createByteArray();
    }

    public static final Creator<Datas> CREATOR = new Creator<Datas>() {
        @Override
        public Datas createFromParcel(Parcel in) {
            return new Datas(in);
        }

        @Override
        public Datas[] newArray(int size) {
            return new Datas[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(username);
        dest.writeByteArray(imageBytes);
    }
}

