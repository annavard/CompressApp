package com.example.annavardanyan.compressapp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.annavardanyan.compressapp.R;
import com.example.annavardanyan.compressapp.model.Media;
import com.example.annavardanyan.compressapp.view.view_holder.BaseViewHolder;
import com.example.annavardanyan.compressapp.view.view_holder.UploadViewHolder;

import java.util.List;


public class UploadAdapter extends RecyclerView.Adapter<BaseViewHolder>{


    private List<Media> mList;

    private Context mContext;

    public UploadAdapter(List<Media> list, Context context){
        mList = list;
        mContext = context;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_upload, parent, false);
        return new UploadViewHolder(view, null);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

        holder.bindData(mList.get(position), mContext);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
