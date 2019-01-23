package com.example.week3daily2broadcastreceivers;


import android.os.Parcel;
import android.os.Parcelable;

public class Alblum implements Parcelable {
    private String song;
    private String title;
    private String year;
    private String image;

    public Alblum() {
    }

    public Alblum(String song, String title, String year, String image) {
        this.song = song;
        this.title = title;
        this.year = year;
        this.image = image;
    }

    protected Alblum(Parcel in) {
        song = in.readString();
        title = in.readString();
        year = in.readString();
        image = in.readString();
    }

    public static final Creator<Alblum> CREATOR = new Creator<Alblum>() {
        @Override
        public Alblum createFromParcel(Parcel in) {
            return new Alblum(in);
        }

        @Override
        public Alblum[] newArray(int size) {
            return new Alblum[size];
        }
    };

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(song);
        dest.writeString(title);
        dest.writeString(year);
        dest.writeString(image);
    }
}

