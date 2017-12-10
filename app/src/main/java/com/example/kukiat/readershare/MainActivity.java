package com.example.kukiat.readershare;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;


    private TextView mText;
    private Button mButton;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(MainActivity.this, NotificationService.class));
        mText = findViewById(R.id.fireId);
        mButton = findViewById(R.id.buttonSignIn);
        fetchData();



        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getEmail();
            Log.i("uid" ,user.getUid().toString());
            mText.setText(uid);
            mButton.setText("SIGN OUT");
        }
    }



    public void goLogIn(View v) {
        if (mButton.getText().toString() == "SIGN OUT"){
            FirebaseAuth.getInstance().signOut();
            mButton.setText("SIGN IN");
            mText.setText("not login");
        }else {
            Intent intent = new Intent(getBaseContext(), LogInActivity.class);
            startActivity(intent);
        }
    }

    public void goPost(View v) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            Intent intent = new Intent(getBaseContext(), PostActivity.class);
            startActivity(intent);

        }else{
            Toast.makeText(MainActivity.this, "You need to Login first",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getBaseContext(), LogInActivity.class);
            startActivity(intent);
        }
    }

    private void fetchData() {
        recyclerView = (RecyclerView) findViewById(R.id.view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final List<ReviewItem> reviewItemList = new ArrayList<>();
        String url = "https://readershare.herokuapp.com/feeds";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray result = response.getJSONArray("result");
                            for(int i=0; i<result.length(); i++) {
                                JSONObject jsonReview = result.getJSONObject(i);
                                JSONObject book = jsonReview.getJSONObject("book");
                                JSONObject review = jsonReview.getJSONObject("review");
                                JSONObject reviewer = jsonReview.getJSONObject("reviewer");

                                String id = jsonReview.getString("id");
                                String bookImage = book.getString("image");
                                String bookName = book.getString("name");
                                String reviewerId = reviewer.getString("id");
                                String reviewerEmail = reviewer.getString("email");
                                String reviewContent = review.getString("content");
                                String reviewTitle = review.getString("title");
                                int reviewLike = review.getInt("like");
                                int reviewRating = review.getInt("rating");
                                int createdAt = jsonReview.getInt("createdAt");

                                ReviewItem reviewItem = new ReviewItem(id, bookImage, bookName,
                                        reviewerId, reviewContent, reviewLike,
                                        reviewRating, reviewTitle, createdAt, reviewerEmail);

                                reviewItemList.add(reviewItem);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter = new MyAdapter(reviewItemList, getApplicationContext());
                        recyclerView.setAdapter(adapter);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError err){
                    }
                });

        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }
}