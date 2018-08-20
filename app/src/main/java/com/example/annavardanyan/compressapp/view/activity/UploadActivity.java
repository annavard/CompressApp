package com.example.annavardanyan.compressapp.view.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.annavardanyan.compressapp.model.Media;
import com.example.annavardanyan.compressapp.R;
import com.example.annavardanyan.compressapp.view.adapter.UploadAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UploadActivity extends AppCompatActivity {

    public static String TAG = "UploadActivity";

    private UploadAdapter mAdapter;


    private List<Media> mList;

    private FirebaseStorage firebaseStorage;

    private StorageReference storageReference;


    @BindView(R.id.recycler_upload)
    RecyclerView recyclerUpload;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_upload);

        ButterKnife.bind(this);

        mList = Parcels.unwrap(getIntent().getParcelableExtra(MediaPickerActivity.SELECTED_MEDIA));
        for (Media media : mList) {
            Log.d(TAG, "title - " + media.getTitle());
            Log.d(TAG, "uri - " + media.getUri().toString());
            Log.d(TAG, "type - " + media.getMediaType());
            Log.d(TAG, "size - " + media.getSize());
            Log.d(TAG, "_____________________________________");
        }

        mAdapter = new UploadAdapter(mList, this);
        recyclerUpload.setAdapter(mAdapter);
        recyclerUpload.setLayoutManager(new LinearLayoutManager(this));


        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();


    }


    private void uploadImage(final int index) {


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading... " + mList.get(index).getTitle());
        progressDialog.show();

        StorageReference ref = storageReference.child(mList.get(index).getTitle());
        ref.putFile(mList.get(index).getUri())
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                        Toast.makeText(UploadActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        listener.uploadFinished(index + 1);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(UploadActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                .getTotalByteCount());
                        progressDialog.setMessage("Uploaded " + (int) progress + "%");
                    }
                });

    }


    @OnClick(R.id.btn_select)
    void upload() {
        int index = 0;
        uploadImage(index);
    }


   private UploadFinishedListener listener = new UploadFinishedListener() {
        @Override
        public void uploadFinished(int index) {
            if (index > mList.size() - 1) return;

            uploadImage(index);
        }
    };


    interface UploadFinishedListener {
        void uploadFinished(int index);
    }

}
