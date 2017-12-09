package com.example.kukiat.readershare;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
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
        final List<ListItem> listItems;

        String url = "https://readershare.herokuapp.com/mock/feeds";
        listItems = new ArrayList<>();

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject;
                        Gson gson = new Gson();
                        for(int i=0; i<response.length(); i++) {
                            try {
                                jsonObject = response.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                String name = jsonObject.getString("name");
                                String topic = jsonObject.getString("topic");
                                String rating = jsonObject.getString("rating");
                                String book = jsonObject.getString("image");

                                ListItem ls = new ListItem(id, name, topic, rating, book);
                                listItems.add(ls);

                            } catch (JSONException e) {
                                Log.d("error", e.getMessage());
                                e.printStackTrace();
                            }

                        }
                        adapter = new MyAdapter(listItems, getApplicationContext());
                        recyclerView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError err) {

                    }
                });
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }
}