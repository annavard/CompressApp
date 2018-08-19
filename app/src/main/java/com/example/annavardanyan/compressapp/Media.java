package com.example.annavardanyan.compressapp;

import android.net.Uri;

import org.parceler.Parcel;


@Parcel
class Media {

    private String title;
    private Uri uri;
    private boolean isSelected;
    int mediaType;
    private int size;


    public Media() {

    }

    public Media(String title, Uri uri, int mediaType, int size) {
        this.title = title;
        this.uri = uri;
        this.mediaType = mediaType;
        this.size = size;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
