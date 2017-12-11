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
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;

import org.json.JSONObject;

/**
 * Created by kukiat on 11/13/2017 AD.
 */

public class PostActivity extends AppCompatActivity {

    private static int TAKE_PHOTO_REQUEST_CODE = 11;
    Uri outputFileUri;

    EditText vPostTitile;
    EditText vPostContent;
    EditText vPostRating;
    EditText vPostBook;
    StorageReference storageReference;
    private FirebaseUser user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_layout);
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            onPrepareData();
        }else{

        }
    }

    public void onPrepareData() {
        vPostTitile = (EditText) findViewById(R.id.post_topic);
        vPostContent = (EditText) findViewById(R.id.post_content);
        vPostRating = (EditText) findViewById(R.id.post_topic);
        vPostBook = (EditText) findViewById(R.id.post_bookname);

        String postTitleData = vPostTitile.getText().toString();
        String postContentData = vPostContent.getText().toString();
        String postRatingData = vPostRating.getText().toString();
        String postBookData = vPostBook.getText().toString();

        JSONObject data = new JSONObject();
//        data.put("uId", user.getUid());
    }

    private boolean validateForm() {
        boolean valid = true;

        String title = vPostTitile.getText().toString();
        if (TextUtils.isEmpty(title)) {
            vPostTitile.setError("Required.");
            valid = false;
        } else {
            vPostTitile.setError(null);
        }

        String content = vPostContent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            vPostContent.setError("Required.");
            valid = false;
        } else {
            vPostContent.setError(null);
        }

        String rating = vPostRating.getText().toString();
        if (TextUtils.isEmpty(rating)) {
            vPostRating.setError("Required.");
            valid = false;
        } else {
            vPostRating.setError(null);
        }

        String book = vPostBook.getText().toString();
        if (TextUtils.isEmpty(book)) {
            vPostBook.setError("Required.");
            valid = false;
        } else {
            vPostBook.setError(null);
        }

        return valid;
    }
}
