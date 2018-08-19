package com.example.annavardanyan.compressapp.helper;

import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import com.example.annavardanyan.compressapp.model.Media;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CursorHelper {


    public static List<Media> getData(AppCompatActivity activity){

        List<Media> list = new ArrayList<>();


        String[] projection = {
                MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns.DATE_ADDED,
                MediaStore.Files.FileColumns.MEDIA_TYPE,
                MediaStore.Files.FileColumns.MIME_TYPE,
                MediaStore.Files.FileColumns.TITLE,
                MediaStore.Files.FileColumns.SIZE
        };

        // Return only video and image metadata.
        String selection = MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
                + " OR "
                + MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

        Uri queryUri = MediaStore.Files.getContentUri("external");

        CursorLoader cursorLoader = new CursorLoader(
                activity,
                queryUri,
                projection,
                selection,
                null, // Selection args (none).
                MediaStore.Files.FileColumns.DATE_ADDED + " DESC" // Sort order.
        );

        Cursor cursor = cursorLoader.loadInBackground();

        int column_index_title = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.TITLE);
        int column_index_data = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA);
        int column_index_type = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MEDIA_TYPE);
        int column_index_size = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE);


        while (cursor.moveToNext()) {
            String absolutePathOfImage = cursor.getString(column_index_data);
            list.add(new Media(cursor.getString(column_index_title),
                    Uri.fromFile(new File(absolutePathOfImage)),
                    cursor.getInt(column_index_type),
                    cursor.getInt(column_index_size)
                    ));
        }

        return list;
    }

}
