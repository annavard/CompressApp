package com.example.annavardanyan.compressapp.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

public class PickerLayoutManager extends GridLayoutManager{

    public PickerLayoutManager(Context context, final int spanCount) {
        super(context, spanCount);
        setSpanSizeLookup(new SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position == 0){
                    return spanCount;
                }else{
                    return 1;
                }
            }
        });
    }



}
