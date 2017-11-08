package com.example.kukiat.readershare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kukiat on 11/6/2017 AD.
 */

public class ShowActivity extends AppCompatActivity {
    TextView tvName;
    TextView tvTopic;
    TextView tvDescription;
    TextView tvRating;
    ImageView ivPicName;
    ImageView ivPicBook;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_list_item);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            int id = bundle.getInt("id");
            fetchGetFeedData(id);
        }
    }

    public void fetchGetFeedData(int id) {
        String url = "http:10.0.2.2:3000/api/feed/"+id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
//                            int id = response.getInt("id");

                            tvDescription = (TextView) findViewById(R.id.desc);
                            tvDescription.setText(response.getString("description"));

                            tvName = (TextView) findViewById(R.id.listTopic);
                            tvName.setText(response.getString("topic"));

                            tvTopic = (TextView) findViewById(R.id.listName);
                            tvTopic.setText(response.getString("name"));
//                            Picasso.with(getApplicationContext()).load(response.getString("imageName")).resize(150, 150).into(ivPicName);
//                            Picasso.with(getApplicationContext()).load(response.getString("imageBook")).resize(350,350).into(ivPicBook);



                        }catch (JSONException e) {
                            Log.d("error", e.getMessage());
                            e.printStackTrace();
                        }
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
