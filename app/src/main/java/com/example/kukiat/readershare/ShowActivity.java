package com.example.kukiat.readershare;

import android.content.Context;
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
//            fetchGetFeedData(id);
        }
    }

    public void fetchGetFeedData(int id) {
        String url = "https://readershare.herokuapp.com/mock/review/"+id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
//                            int id = response.getInt("id");

                            tvDescription = (TextView) findViewById(R.id.review_content_show);
                            tvDescription.setText(response.getString("description"));

                            tvName = (TextView) findViewById(R.id.review_title_show);
                            tvName.setText(response.getString("topic"));

                            tvTopic = (TextView) findViewById(R.id.reviewer_name_show);
                            tvTopic.setText(response.getString("name"));
                            Log.i("bookss",response.getString("imageName"));
//                            Picasso.with(getBaseContext()).load(response.getString("imageName")).resize(150, 150).error(R.drawable.blurbook).into(ivPicName);
//                            Picasso.with(getBaseContext()).load(response.getString("imageBook")).resize(350,350).error(R.drawable.blurbook).into(ivPicBook);



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
