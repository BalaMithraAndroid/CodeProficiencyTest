package com.example.dellpc.facts.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Balamithra on 2/11/2018.
 */

public class FactsList implements Parcelable{
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

    @SerializedName("imageHref")
    @Expose
    private String imageHref;

    protected FactsList(Parcel in) {
        readFromParcel(in);
    }
    private void readFromParcel(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.imageHref = in.readString();
    }
    public static final Creator<FactsList> CREATOR = new Creator<FactsList>() {
        @Override
        public FactsList createFromParcel(Parcel in) {
            return new FactsList(in);
        }

        @Override
        public FactsList[] newArray(int size) {
            return new FactsList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.description);
        parcel.writeString(this.imageHref);
    }
}