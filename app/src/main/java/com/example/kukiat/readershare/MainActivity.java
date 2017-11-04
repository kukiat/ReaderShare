package com.example.kukiat.readershare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private CustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);

        fetchData();

    }

    private void fetchData() {
        String url = "http://blog.teamtreehouse.com/api/get_recent_summary/";
//        String url = "http://itpart.com/android/json/test8records.php";
        String jsonString ="";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray data = response.getJSONArray("posts");
                            for(int i=0; i<data.length(); i++) {
                                JSONObject obj = data.getJSONObject(i);
                                Log.i("xxx", obj.getString("title"));
                            }
                        }catch (JSONException e) {
                            Log.d("error", e.getMessage());
                            e.printStackTrace();
                        }

                        Log.i("info", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError err){
                    }
                });
        Volley.newRequestQueue(this).add(jsonObjectRequest);

//        Gson gson = new Gson();
//        Blog blog = gson.fromJson(jsonString, Blog.class);
//
//        mAdapter = new CustomAdapter(this, blog.getPosts());
//        mListView.setAdapter(mAdapter);
    }

}