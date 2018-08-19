package com.example.annavardanyan.compressapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class PickerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static String TAG = "PickerAdapter";

    private static int VIEW_TYPE_HEADER = 1;

    private List<Media> mList;

    private Context mContext;

    private PickerHeaderViewHolder.OnDoneClickedListener mListener;


    public PickerAdapter(List<Media> list, Context context,  BaseViewHolder.OnDoneClickedListener listener) {
        mList = list;
        mContext = context;
        mListener = listener;
    }



    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Log.d(TAG, "onCreateViewHolder - viewType - " + viewType);
        View view;
        if(viewType == VIEW_TYPE_HEADER){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_picker_header, null, false);
            return new PickerHeaderViewHolder(view, mListener);
        }else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_picker, null, false);
            return new PickerViewHolder(view, mListener);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bindData(mList.get(position), mContext);
    }


    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }


    public void updateItem(Media media) {
        int position = mList.indexOf(media);
        notifyItemChanged(position);
        notifyItemChanged(0);

    }

    @Override
    public int getItemViewType(int position) {
//        Log.d(TAG, "getItemViewType - position - " + position);
        if(position == 0){
            return VIEW_TYPE_HEADER;
        }
        return super.getItemViewType(position);
    }



}
