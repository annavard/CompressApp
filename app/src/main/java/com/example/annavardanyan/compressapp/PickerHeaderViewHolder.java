package com.example.annavardanyan.compressapp;

import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PickerHeaderViewHolder extends BaseViewHolder {




    public PickerHeaderViewHolder(View itemView, OnDoneClickedListener listener) {
        super(itemView, listener);

        ButterKnife.bind(this, itemView);
    }


    @OnClick(R.id.img_done)
    void done(){
        mListener.onDoneClicked();
    }

}
