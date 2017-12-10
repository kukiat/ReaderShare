package com.example.kukiat.readershare;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private FirebaseUser user;

    private NavigationView vNavigationView;
    private Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(MainActivity.this, NotificationService.class));
//        mButton = findViewById(R.id.buttonSignIn);
        vNavigationView = findViewById(R.id.navigation_view);
        menu = vNavigationView.getMenu();
        fetchData();
        toggleTab();

        if (user != null) { //ลอกอินแล้ว
            menu.findItem(R.id.edit_profile_menu).setVisible(true);
            menu.findItem(R.id.bookmark_menu).setVisible(true);
            menu.findItem(R.id.logout).setVisible(true);
            menu.findItem(R.id.signIn).setVisible(false);
//            menu.findItem(R.id.edit_profile_menu).setOnMenuItemClickListener(new View.o)
            String uid = user.getEmail();
//            mButton.setText("SIGN OUT");
        }else{
            menu.findItem(R.id.edit_profile_menu).setVisible(false);
            menu.findItem(R.id.bookmark_menu).setVisible(false);
            menu.findItem(R.id.logout).setVisible(false);
            menu.findItem(R.id.signIn).setVisible(true);
        }

    }

    public void toggleTab() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goLogIn(View v) {
        if (user != null){
            FirebaseAuth.getInstance().signOut();
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