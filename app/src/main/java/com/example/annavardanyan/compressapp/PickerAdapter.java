package com.example.annavardanyan.compressapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class PickerAdapter extends RecyclerView.Adapter<PickerViewHolder> {

    private List<Media> mList;

    private Context mContext;

    private PickerViewHolder.OnItemSelectedListener mListener;


    public PickerAdapter(List<Media> list, Context context, PickerViewHolder.OnItemSelectedListener listener) {
        mList = list;
        mContext = context;
        mListener = listener;
    }

    public PickerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_picker, null, false);
        return new PickerViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PickerViewHolder pickerViewHolder, int i) {
        pickerViewHolder.bindData(mList.get(i), mContext);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void updateItem(Media media) {
        int position = mList.indexOf(media);
        notifyItemChanged(position);

    }
}
