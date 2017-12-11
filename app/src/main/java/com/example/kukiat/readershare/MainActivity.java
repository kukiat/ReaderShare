package com.example.kukiat.readershare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private FirebaseUser user;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView vNavigationView;
    View headerView;
    private Menu menu;
    TextView vCurrentEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setNavigationViewListener();
        startService(new Intent(MainActivity.this, NotificationService.class));
        vNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        menu = vNavigationView.getMenu();
        user = FirebaseAuth.getInstance().getCurrentUser();
        fetchData();
        toggleTab();
        clearMenu();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        switch (item.getItemId()) {
            case R.id.edit_profile_menu: {
                Intent intent = new Intent(getBaseContext(), EditProfileActivity.class);
                intent.putExtra("name", user.getDisplayName());
                startActivity(intent);
                break;
            }
            case R.id.bookmark_menu: {
                Intent intent = new Intent(getBaseContext(), BookmarkActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.subscribe_menu: {
                Intent intent = new Intent(getBaseContext(), SubscribeActivity.class);
                intent.putExtra("uid", user.getUid());
                startActivity(intent);
                break;
            }
            case R.id.logout: {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Logout Success",
                        Toast.LENGTH_SHORT).show();
                clearMenu();
                break;
            }
            case R.id.signIn: {
                Intent intent = new Intent(getBaseContext(), LogInActivity.class);
                startActivity(intent);
                break;
            }
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void clearMenu() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            menu.findItem(R.id.edit_profile_menu).setVisible(true);
            menu.findItem(R.id.bookmark_menu).setVisible(true);
            menu.findItem(R.id.logout).setVisible(true);
            menu.findItem(R.id.subscribe_menu).setVisible(true);
            menu.findItem(R.id.signIn).setVisible(false);
        }else{
            menu.findItem(R.id.edit_profile_menu).setVisible(false);
            menu.findItem(R.id.bookmark_menu).setVisible(false);
            menu.findItem(R.id.logout).setVisible(false);
            menu.findItem(R.id.subscribe_menu).setVisible(false);
            menu.findItem(R.id.signIn).setVisible(true);
        }

    }
    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void toggleTab() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

                                String reviewerId = reviewer.getString("uId");
                                String reviewerEmail = reviewer.getString("email");
                                String reviewerImage = reviewer.getString("image");
                                String reviewerName = reviewer.getString("name");

                                String reviewContent = review.getString("content");
                                String reviewTitle = review.getString("title");
                                int reviewLike = review.getInt("like");
                                int reviewRating = review.getInt("rating");
                                int createdAt = jsonReview.getInt("createdAt");

                                ReviewItem reviewItem = new ReviewItem(id, bookImage, bookName,
                                        reviewerId, reviewContent, reviewLike,
                                        reviewRating, reviewTitle, createdAt, reviewerEmail
                                        ,reviewerImage, reviewerName);

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