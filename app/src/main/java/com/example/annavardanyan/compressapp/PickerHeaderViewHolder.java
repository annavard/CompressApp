package com.example.annavardanyan.compressapp;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PickerHeaderViewHolder extends BaseViewHolder {

    @BindView(R.id.txt_count)
    TextView textCount;



    PickerHeaderViewHolder(View itemView, OnDoneClickedListener listener) {
        super(itemView, listener);

        ButterKnife.bind(this, itemView);
    }


    @Override
    protected void bindData(Media media, Context context) {
        super.bindData(media, context);
        textCount.setText(String.valueOf(MediaPickerActivity.selectedItemCount));
    }

    @OnClick(R.id.img_done)
    void done(){
        mListener.onDoneClicked();
    }

}
