package com.example.annavardanyan.compressapp.view.view_holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.annavardanyan.compressapp.R;
import com.example.annavardanyan.compressapp.helper.CompressHelper;
import com.example.annavardanyan.compressapp.model.Media;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UploadViewHolder extends BaseViewHolder{

    @BindView(R.id.img_upload)
    ImageView imageUpload;

    @BindView(R.id.txt_title)
    TextView textTitle;

    @BindView(R.id.txt_size)
    TextView textSize;


    public UploadViewHolder(View itemView, OnDoneClickedListener listener) {
        super(itemView, listener);

        ButterKnife.bind(this, itemView);
    }


    public void bindData(Media media, Context context){

        textTitle.setText(media.getTitle());
        String sizeMb = String.valueOf(CompressHelper.bytesToMB(media.getSize()));
        String sizeText = context.getResources().getString(R.string.size,CompressHelper.bytesToMB(media.getSize()));
        textSize.setText(sizeText);

        Glide.with(context)
                .load(media.getUri())
//                .apply(new RequestOptions().override(300, 300))
                .apply(new RequestOptions().placeholder(R.drawable.img_placeholder))
                .into(imageUpload);

    }
}
