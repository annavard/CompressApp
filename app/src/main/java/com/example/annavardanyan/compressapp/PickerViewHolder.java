package com.example.annavardanyan.compressapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

class PickerViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "PickerViewHolder";

    interface OnItemSelectedListener{

        void onItemSelected(Media media);
    }



//    @BindView(R.id.txt_name_media)
//    TextView textMediaName;

    @BindView(R.id.img_media)
    ImageView imageMedia;

    @BindView(R.id.img_media_selected)
    ImageView imageMediaSelected;


    private OnItemSelectedListener mListener;

    private Media mMedia;



    PickerViewHolder(@NonNull View itemView, OnItemSelectedListener listener) {
        super(itemView);

        ButterKnife.bind(this, itemView);
        mListener = listener;
    }


    public void bindData(Media media, Context context) {

        if (media == null) return;
//        textMediaName.setText(media.getName());
        mMedia = media;



        Glide.with(context)
                .load(media.getUri())
                .apply(new RequestOptions().override(300, 300))
                .apply(new RequestOptions().placeholder(R.drawable.img_placeholder))
                .into(imageMedia);

        if(mMedia.isSelected()){
            imageMediaSelected.setVisibility(View.VISIBLE);
        }else {
            imageMediaSelected.setVisibility(View.GONE);
        }

    }




    @OnClick(R.id.picker_item_root)
    void onMediaSelected(){
        Log.d(TAG, "onMediaSelected - isSelected - " + mMedia.isSelected());
        mMedia.setSelected(!mMedia.isSelected());
        mListener.onItemSelected(mMedia);

    }
















//TODO; Delete
//    private void resize(){
//        Bitmap bitmap = null;
//        try {
//            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), media.getUri());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if(bitmap != null){
//
//            Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
//            imageMedia.setImageBitmap(resizedBitmap);
//        }
//    }
}