package com.example.kukiat.readershare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
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

    private List<ListItem> listItems;

    private TextView mText;

    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = findViewById(R.id.fireId);
        mButton = findViewById(R.id.buttonSignIn);
        fetchData();
        Log.i("Res", "Main");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
//            // Name, email address, and profile photo Url
            String email = user.getEmail();
            mText.setText(email);
//
//            // The user's ID, unique to the Firebase project. Do NOT use this value to
//            // authenticate with your backend server, if you have one. Use
//            // FirebaseUser.getToken() instead.
//            FirebaseUser user = firebaseAuth.getCurrentUser().getIdToken(true).toString();
//            String uid = FirebaseUser.getIdToken();

//            mText.setText(email);
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
        Intent intent = new Intent(getBaseContext(), PostActivity.class);
        startActivity(intent);
    }

    private void fetchData() {
        recyclerView = (RecyclerView) findViewById(R.id.view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String url = "https://readershare.herokuapp.com/feeds";
        listItems = new ArrayList<>();

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject;

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
                            adapter = new MyAdapter(listItems, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        }
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