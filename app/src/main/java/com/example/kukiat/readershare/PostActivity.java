package com.example.kukiat.readershare;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

/**
 * Created by kukiat on 11/13/2017 AD.
 */

public class PostActivity extends AppCompatActivity {

    private static int TAKE_PHOTO_REQUEST_CODE = 99;
    Uri outputFileUri;
    StorageReference storageReference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_layout);
    }

    public void takePhoto(View v) {
        File StorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera");
        File file = new File(StorageDir.getPath() + File.separator + "test4.jpg");
        outputFileUri = Uri.fromFile(file);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        Log.i("wdwd", outputFileUri.toString());
        startActivityForResult(intent, TAKE_PHOTO_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if(requestCode == TAKE_PHOTO_REQUEST_CODE && resultCode == RESULT_OK) {
            Uri xxx = intent.getData();
            StorageReference path = storageReference.child("photo").child(xxx.getLastPathSegment());

            path.putFile(xxx).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getApplicationContext(), "upload success", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
