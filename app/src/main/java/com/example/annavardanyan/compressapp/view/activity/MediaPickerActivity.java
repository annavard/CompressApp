package com.example.annavardanyan.compressapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.annavardanyan.compressapp.helper.CursorHelper;
import com.example.annavardanyan.compressapp.model.Media;
import com.example.annavardanyan.compressapp.view.adapter.PickerAdapter;
import com.example.annavardanyan.compressapp.view.PickerLayoutManager;
import com.example.annavardanyan.compressapp.R;
import com.example.annavardanyan.compressapp.view.view_holder.PickerHeaderViewHolder;

import org.parceler.Parcels;

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
        mList = CursorHelper.getData(this);


        for (Media media : mList) {
            Log.d(TAG, "title - " + media.getTitle());
            Log.d(TAG, "uri - " + media.getUri().toString());
            Log.d(TAG, "type - " + media.getMediaType());
            Log.d(TAG, "size - " + media.getSize());
            Log.d(TAG, "_____________________________________");
        }

        mAdapter = new PickerAdapter(mList, this, this);
        mRecycler.setAdapter(mAdapter);
        PickerLayoutManager layoutManager = new PickerLayoutManager(this, 3);
        mRecycler.setLayoutManager(layoutManager);

        selectedItemCount = 0;

    }





    @Override
    public void onItemSelected(Media media) {
        Log.d(TAG, "onItemSelected");

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
//        Log.d(TAG, "onDoneClicked");
        Intent intent = new Intent(MediaPickerActivity.this, UploadActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("User", Parcels.wrap(mSelectedList));
        intent.putExtra(SELECTED_MEDIA, Parcels.wrap(mSelectedList));
        startActivity(intent);

    }
}
