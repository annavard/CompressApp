package com.example.annavardanyan.compressapp;

import android.net.Uri;

class Media {

    private String name;
    private Uri uri;
    private boolean isSelected;

    public Media(String name, Uri uri) {
        this.name = name;
        this.uri = uri;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
