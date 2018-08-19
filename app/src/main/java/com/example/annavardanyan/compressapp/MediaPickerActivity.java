package com.example.annavardanyan.compressapp;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MediaPickerActivity extends AppCompatActivity implements  PickerHeaderViewHolder.OnDoneClickedListener {

    public static String TAG = "MediaPickerActivity";

    public static int MAX_COUNT = 5;
    public static int MIN_COUNT = 1;

    private PickerAdapter mAdapter;
    private List<Media> mList = new ArrayList<>();
    private PickerLayoutManager mLayoutManager;
    private int selectedItemCount;

    @BindView(R.id.recycler_picker)
    RecyclerView mRecycler;

//    @BindView(R.id.txt_count)
//    TextView txtCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_picker);

        ButterKnife.bind(this);

        //TODO; Remove this
        getAllShownImagesPath(this);
        mAdapter = new PickerAdapter(mList, this, this);
        mRecycler.setAdapter(mAdapter);
//        mLayoutManager = new GridLayoutManager(this, 3);
        mLayoutManager = new PickerLayoutManager(this, 3);
        mRecycler.setLayoutManager(mLayoutManager);

    }


    private void getAllShownImagesPath(Activity activity) {
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        ArrayList<String> listOfAllImages = new ArrayList<>();
        String absolutePathOfImage = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;


        String[] projection = {MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        cursor = activity.getContentResolver().query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

        cursor.moveToLast();

        while (cursor.moveToPrevious()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            mList.add(new Media(cursor.getString(column_index_folder_name), Uri.fromFile(new File(absolutePathOfImage))));
        }
    }

    @Override
    public void onItemSelected(Media media) {

        if (media.isSelected()) {
            if (selectedItemCount >= MAX_COUNT) {
                Log.d(TAG, "selectedItemCount - " + selectedItemCount);
                Toast.makeText(this, "Sorry. Up to 5 files can be selected", Toast.LENGTH_SHORT).show();
                return;
            }
            selectedItemCount++;
            Log.d(TAG, "selectedItemCount - " + selectedItemCount);
            mAdapter.updateItem(media);
//            txtCount.setText(String.valueOf(selectedItemCount));
        } else {
            if (selectedItemCount >= MIN_COUNT) {
                selectedItemCount--;
                Log.d(TAG, "selectedItemCount - " + selectedItemCount);
                mAdapter.updateItem(media);
//                txtCount.setText(String.valueOf(selectedItemCount));
            }
        }


    }



    @Override
    public void onDoneClicked() {
        Log.d(TAG, "onDoneClicked");

    }
}
