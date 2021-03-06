package com.example.annavardanyan.compressapp.view.view_holder;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.annavardanyan.compressapp.model.Media;
import com.example.annavardanyan.compressapp.view.adapter.PickerAdapter;
import com.example.annavardanyan.compressapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoPickerViewHolder extends BaseViewHolder {

    private static final String TAG = "VideoPickerViewHolder";

    private Media mMedia;


    @BindView(R.id.video)
    ImageView videoView;

    @BindView(R.id.img_video_selected)
    ImageView imageMediaSelected;


    public VideoPickerViewHolder(View itemView, OnDoneClickedListener listener) {
        super(itemView, listener);

        ButterKnife.bind(this, itemView);
    }


    @Override
    public void bindData(Media media, Context context) {
        super.bindData(media, context);
        Log.d(PickerAdapter.TAG, "VideoPickerViewHolder - bindData");


        mMedia = media;


        Glide.with(context)
                .load(media.getUri())
//                .apply(new RequestOptions().override(300, 300))
                .apply(new RequestOptions().placeholder(R.drawable.img_placeholder))
                .into(videoView);

        if (mMedia.isSelected()) {
            imageMediaSelected.setVisibility(View.VISIBLE);
        } else {
            imageMediaSelected.setVisibility(View.GONE);
        }
    }


    @OnClick(R.id.video_item_root)
    void onMediaSelected() {
        Log.d(TAG, "onMediaSelected - isSelected - " + mMedia.isSelected());
        mMedia.setSelected(!mMedia.isSelected());
        mListener.onItemSelected(mMedia);

    }
}
