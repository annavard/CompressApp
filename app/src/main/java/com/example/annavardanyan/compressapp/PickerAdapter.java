package com.example.annavardanyan.compressapp;

import android.content.Context;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class PickerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static String TAG = "PickerAdapter";

    private static final int VIEW_TYPE_HEADER = 1;
    private static final int VIEW_TYPE_IMAGE = 2;
    private static final int VIEW_TYPE_VIDEO = 3;

    private List<Media> mList;

    private Context mContext;

    private PickerHeaderViewHolder.OnDoneClickedListener mListener;


    public PickerAdapter(List<Media> list, Context context, BaseViewHolder.OnDoneClickedListener listener) {
        mList = list;
        mContext = context;
        mListener = listener;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Log.d(TAG, "onCreateViewHolder - viewType - " + viewType);
        View view = null;

        switch (viewType) {
            case VIEW_TYPE_HEADER:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_picker_header, null, false);
                return new PickerHeaderViewHolder(view, mListener);
            case VIEW_TYPE_IMAGE:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_picker_image, null, false);
                return new ImagePickerViewHolder(view, mListener);
            case VIEW_TYPE_VIDEO:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_picker_video, null, false);
                return new VideoPickerViewHolder(view, mListener);
        }

        return new BaseViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder - position - " + position);
        holder.bindData(mList.get(position), mContext);
    }


    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }


    public void updateItem(Media media) {
        int position = mList.indexOf(media);
        Log.d(TAG, "updateItem - position - " + position);
        notifyItemChanged(position);
        notifyItemChanged(0);

    }

    @Override
    public int getItemViewType(int position) {
//        Log.d(TAG, "getItemViewType - position - " + position);
//        Log.d(TAG, "getItemViewType - MediaType - " + mList.get(position).getMediaType());
        if (position == 0) {
            return VIEW_TYPE_HEADER;
        } else if (mList.get(position + 1).getMediaType() == MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE) {
//            Log.d(TAG, "getItemViewType - MEDIA_TYPE_IMAGE - position - " + position);
            return VIEW_TYPE_IMAGE;
        } else if (mList.get(position + 1).getMediaType() == MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO) {
//            Log.d(TAG, "getItemViewType - MEDIA_TYPE_VIDEO - position - " + position);
            return VIEW_TYPE_VIDEO;
        }

        return super.getItemViewType(position);
    }


}
