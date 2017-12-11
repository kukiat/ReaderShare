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


    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private FirebaseUser user;
    ProgressDialog dialog;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);
<<<<<<< HEAD
        user = FirebaseAuth.getInstance().getCurrentUser();
        dialog = ProgressDialog.show(SubscribeActivity.this, "","Loading. Please wait...", true);
        fetchDataSubscribe();
    }

    public void fetchDataSubscribe() {

        recyclerView = (RecyclerView) findViewById(R.id.subscribe_list_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final List<SubscribeItem> subscribeItemList = new ArrayList<>();
        String url = "https://readershare.herokuapp.com/getprofile/"+user.getUid();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response){
                        try {
                            JSONArray allSubscribe = response.getJSONArray("subscribe");
                            for(int i=0 ;i<allSubscribe.length(); i++) {
                                JSONObject subscribe = allSubscribe.getJSONObject(i);
                                String subscribeId = subscribe.getString("uId");
                                String subscribeEmail = subscribe.getString("email");
                                String subscribeName = subscribe.getString("name");
                                String subscribeImage = subscribe.getString("image");
                                SubscribeItem subscribeItem = new SubscribeItem(subscribeId, subscribeName, subscribeImage, subscribeEmail);
                                subscribeItemList.add(subscribeItem);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                        adapter = new SubscribeAdapter(subscribeItemList, getApplicationContext());
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
=======
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            String uid = bundle.getString("uid");
            Log.i("uid", uid);

        }
    }

>>>>>>> 672fc894d6b8db0aaba8fe709043792a1494a3d2

}
