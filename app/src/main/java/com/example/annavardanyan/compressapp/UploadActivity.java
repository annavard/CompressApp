package com.example.annavardanyan.compressapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.parceler.Parcels;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class UploadActivity extends AppCompatActivity{

    public static String TAG = "UploadActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_upload);

        ButterKnife.bind(this);

        List<Media> mediaList = Parcels.unwrap(getIntent().getParcelableExtra(MediaPickerActivity.SELECTED_MEDIA));
        for(Media media : mediaList){
            Log.d(TAG, media.getUri().toString());
        }
    }


    @OnClick(R.id.btn_select)
    void upload(){

    }

}
