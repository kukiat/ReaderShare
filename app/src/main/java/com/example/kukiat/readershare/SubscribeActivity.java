package com.example.kukiat.readershare;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kukiat on 11/12/2017 AD.
 */

public class SubscribeActivity extends AppCompatActivity {

    private FirebaseUser user;
    private TextView vProfileName;
    private TextView vProfileEmail;
    private ImageView vProfileImage;
    private TextView vProfileSlogan;
    private ImageButton vProfileSubscribe;
    private ImageButton vImageButton;
    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    ProgressDialog dialog;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            String uid = bundle.getString("uid");
            Log.i("uid", uid);

        }
    }


}
