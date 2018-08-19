package com.example.annavardanyan.compressapp.view.view_holder;

import android.content.Context;
import android.support.annotation.NonNull;
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

public class ImagePickerViewHolder extends BaseViewHolder {

    private static final String TAG = "ImagePickerViewHolder";

//    @BindView(R.id.txt_name_media)
//    TextView textMediaName;

    @BindView(R.id.img_media)
    ImageView imageMedia;


    @BindView(R.id.img_media_selected)
    ImageView imageMediaSelected;



    private Media mMedia;


    public ImagePickerViewHolder(@NonNull View itemView, OnDoneClickedListener listener) {
        super(itemView, listener);

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindData(Media media, Context context) {
        super.bindData(media, context);
        Log.d(PickerAdapter.TAG, "ImagePickerViewHolder - bindData");
        if (media == null) return;
//        textMediaName.setText(media.getName());
        mMedia = media;


        Glide.with(context)
                .load(media.getUri())
//                .apply(new RequestOptions().override(300, 300))
                .apply(new RequestOptions().placeholder(R.drawable.img_placeholder))
                .into(imageMedia);

        if (mMedia.isSelected()) {
            imageMediaSelected.setVisibility(View.VISIBLE);
        } else {
            imageMediaSelected.setVisibility(View.GONE);
        }
    }




    @OnClick(R.id.picker_item_root)
    void onMediaSelected() {
        Log.d(TAG, "onMediaSelected - isSelected - " + mMedia.isSelected());
        mMedia.setSelected(!mMedia.isSelected());
        mListener.onItemSelected(mMedia);

    }




}
