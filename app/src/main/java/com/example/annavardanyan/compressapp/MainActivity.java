package com.example.annavardanyan.compressapp;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "MainActivity";
    public static int SELECT_PICTURE = 1;

    @BindView(R.id.btn_select)
    Button buttonSelect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);



    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @OnClick(R.id.btn_select)
    void onSelectClicked(){
        Log.d(TAG, "onSelectClicked");
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
    }
}
