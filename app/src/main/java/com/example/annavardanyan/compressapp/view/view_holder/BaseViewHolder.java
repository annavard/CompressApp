package com.example.annavardanyan.compressapp.view.view_holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.annavardanyan.compressapp.model.Media;

public class BaseViewHolder extends RecyclerView.ViewHolder {

    public interface OnDoneClickedListener {

        void onItemSelected(Media media);

        void onDoneClicked();

    }


    OnDoneClickedListener mListener;


    public BaseViewHolder(View itemView, OnDoneClickedListener listener) {
        super(itemView);

        mListener = listener;
    }


    public void bindData(Media media, Context context){
//        Log.d(PickerAdapter.TAG, "BaseViewHolder - bindData");
    }

}
