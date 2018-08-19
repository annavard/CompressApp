package com.example.annavardanyan.compressapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class BaseViewHolder extends RecyclerView.ViewHolder {

    interface OnDoneClickedListener {

        void onItemSelected(Media media);

        void onDoneClicked();
    }


    protected OnDoneClickedListener mListener;


    public BaseViewHolder(View itemView,  OnDoneClickedListener listener) {
        super(itemView);

        mListener = listener;
    }


    protected void bindData(Media media, Context context){
        Log.d(PickerAdapter.TAG, "BaseViewHolder - bindData");
    }
}
