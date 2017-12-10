package com.example.kukiat.readershare;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import com.google.firebase.storage.StorageReference;

/**
 * Created by kukiat on 11/13/2017 AD.
 */

public class PostActivity extends AppCompatActivity {

    private static int TAKE_PHOTO_REQUEST_CODE = 11;
    Uri outputFileUri;
    StorageReference storageReference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_layout);
    }

    public void takePhoto(View v) {

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(i, 111);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
//        Log.i("resultCode",String.valueOf(resultCode));
//        if(requestCode == TAKE_PHOTO_REQUEST_CODE && resultCode == RESULT_OK) {
//            Uri xxx = intent.getData();
//            StorageReference path = storageReference.child("photo").child(xxx.getLastPathSegment());
//
//            path.putFile(xxx).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    Toast.makeText(getApplicationContext(), "upload success", Toast.LENGTH_LONG).show();
//                }
//            });
//        }
    }

}
