package com.example.annavardanyan.compressapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.parceler.Parcels;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MediaPickerActivity extends AppCompatActivity implements PickerHeaderViewHolder.OnDoneClickedListener {

    public static String TAG = "MediaPickerActivity";
    public static String SELECTED_MEDIA = "selected_media";
    //TODO; Another solution
    public static int selectedItemCount;
    public static int MAX_COUNT = 5;
    public static int MIN_COUNT = 1;

    private PickerAdapter mAdapter;
    private List<Media> mList = new ArrayList<>();
    private List<Media> mSelectedList = new ArrayList<>();


    @BindView(R.id.recycler_picker)
    RecyclerView mRecycler;


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
        PickerLayoutManager layoutManager = new PickerLayoutManager(this, 3);
        mRecycler.setLayoutManager(layoutManager);

        selectedItemCount = 0;

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

    public int getSelectedItemCount() {
        return selectedItemCount;
    }

    @Override
    public void onItemSelected(Media media) {


        media.setSelected(!media.isSelected());

        if (media.isSelected()) {
            if (selectedItemCount >= MAX_COUNT) {
                Log.d(TAG, "selectedItemCount - " + selectedItemCount);
                Toast.makeText(this, "Sorry. Up to 5 files can be selected", Toast.LENGTH_SHORT).show();
                media.setSelected(!media.isSelected());
                return;
            }
            selectedItemCount++;
            Log.d(TAG, "selectedItemCount - " + selectedItemCount);
            mAdapter.updateItem(media);
            mSelectedList.add(media);
            return;
        }


        if (selectedItemCount >= MIN_COUNT) {
            selectedItemCount--;
            Log.d(TAG, "selectedItemCount - " + selectedItemCount);
            mAdapter.updateItem(media);
            mSelectedList.remove(media);
        }


    }


    @Override
    public void onDoneClicked() {
        Log.d(TAG, "onDoneClicked");
        Intent intent = new Intent(MediaPickerActivity.this, UploadActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("User", Parcels.wrap(mSelectedList));
        intent.putExtra(SELECTED_MEDIA, Parcels.wrap(mSelectedList));
        startActivity(intent);

    }
}
