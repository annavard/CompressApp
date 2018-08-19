package com.example.annavardanyan.compressapp.view.view_holder;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.annavardanyan.compressapp.model.Media;
import com.example.annavardanyan.compressapp.view.activity.MediaPickerActivity;
import com.example.annavardanyan.compressapp.view.adapter.PickerAdapter;
import com.example.annavardanyan.compressapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PickerHeaderViewHolder extends BaseViewHolder {

    @BindView(R.id.txt_count)
    TextView textCount;



    public PickerHeaderViewHolder(View itemView, OnDoneClickedListener listener) {
        super(itemView, listener);

        ButterKnife.bind(this, itemView);
    }


    @Override
    public void bindData(Media media, Context context) {
        super.bindData(media, context);
        Log.d(PickerAdapter.TAG, "PickerHeaderViewHolder - bindData");
        textCount.setText(String.valueOf(MediaPickerActivity.selectedItemCount));
    }

    @OnClick(R.id.img_done)
    void done(){
        mListener.onDoneClicked();
    }

}
