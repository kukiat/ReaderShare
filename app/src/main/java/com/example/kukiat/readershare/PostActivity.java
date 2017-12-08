package com.example.kukiat.readershare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by kukiat on 11/13/2017 AD.
 */

public class PostActivity extends AppCompatActivity {

    TextView uid;
    FirebaseUser user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_layout);
        uid = findViewById(R.id.uid);

        if(user != null){
            uid.setText(user.getUid());
        }
    }
}
