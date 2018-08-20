package com.example.annavardanyan.compressapp.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.annavardanyan.compressapp.model.Media;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;

public class CompressHelper {

    public static final String TAG = "CompressHelper";

    public static String bytesToMB(int bytes) {
        Log.d(TAG, "bytes - " + bytes);
        Log.d(TAG, "bytes / 1000000 - " + bytes / 1000000);
        Log.d(TAG, "format - " + String.format("%.1s", String.valueOf(bytes / 1000000)));
        return String.format("%.1s", String.valueOf(bytes / 1000000));
    }


    public static Uri compress(Context context, Media media) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), media.getUri());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] byteArray = stream.toByteArray();
        Bitmap compressedBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), compressedBitmap, "compressed_" + media.getTitle(), null);
        return Uri.parse(path);
    }

}
