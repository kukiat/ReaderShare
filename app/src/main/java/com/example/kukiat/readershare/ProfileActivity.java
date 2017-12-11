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

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by kukiat on 10/12/2017 AD.
 */

public class ProfileActivity extends AppCompatActivity {

    private FirebaseUser user;
    private TextView vProfileName;
    private TextView vProfileEmail;
    private ImageView vProfileImage;
    private TextView vProfileSlogan;
    private ImageButton vProfileSubscribe;
    private ImageButton vSubBtn;
    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    ProgressDialog dialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Bundle bundle = getIntent().getExtras();
        dialog = ProgressDialog.show(ProfileActivity.this, "","Loading. Please wait...", true);
        if(bundle!=null) {
            String id = bundle.getString("id");
            Log.i("profile", id);
            fetchDataProfile(id);

        }
    }
    public void fetchDataProfile(final String id) {
        recyclerView = (RecyclerView) findViewById(R.id.reviewer_list_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String url = "https://readershare.herokuapp.com/getprofile/"+id;
        final List<ReleaseItem> releaseItemList = new ArrayList<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        vProfileName = (TextView) findViewById(R.id.profile_name);
                        vProfileEmail = (TextView) findViewById(R.id.profile_email);
                        vProfileImage = (ImageView) findViewById(R.id.profile_image);
                        vProfileSlogan = (TextView) findViewById(R.id.profile_slogan);
                        vSubBtn = (ImageButton) findViewById(R.id.subscribe_btn);
                        vSubBtn.setImageResource(R.drawable.ic_star_black_18dp);
                        fetchSubscribe(id);
                        try {
                            JSONObject profile = response.getJSONObject("profile");
                            JSONArray post = response.getJSONArray("posts");

                            String name = profile.getString("name");
                            String email = profile.getString("email");
                            String image = profile.getString("image");

                            dialog.dismiss();
                            vProfileEmail.setText(email);
                            vProfileSlogan.setText("Liverpool is The BEST. YNWA");

                            if(name.isEmpty())
                                vProfileName.setText(email);
                            else
                                vProfileName.setText(name);
                            if(image.isEmpty())
                                Picasso.with(getApplicationContext()).load("https://www.sparklabs.com/forum/styles/comboot/theme/images/default_avatar.jpg").into(vProfileImage);
                            else
                                Picasso.with(getApplicationContext()).load(image).into(vProfileImage);

                            for(int i=0; i<post.length();i++) {
                                JSONObject res = post.getJSONObject(i);

                                String postId = res.getString("id");
                                int timeStamp = res.getInt("createdAt");

                                JSONObject book = res.getJSONObject("book");
                                String bookImage = book.getString("image");

                                JSONObject review = res.getJSONObject("review");
                                String reviewTitle = review.getString("title");

                                JSONObject reviewer = res.getJSONObject("reviewer");
                                String reviewerId = reviewer.getString("id");

                                ReleaseItem releaseItem = new ReleaseItem(postId, bookImage, reviewerId, reviewTitle, timeStamp);
                                releaseItemList.add(releaseItem);
                                adapter = new ReleaseAdapter(releaseItemList, getApplicationContext());
                                recyclerView.setAdapter(adapter);

                            }

                            vSubBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    try {
                                        onClickSubscribe(id, user.getUid());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });


                        } catch (JSONException e) {
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

    public void fetchSubscribe(final String id) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            String url = "https://readershare.herokuapp.com/getSubscribe/"+user.getUid();

            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray subscribe = response.getJSONArray("result");
                                for(int i=0;i<subscribe.length(); i++) {
                                    if(subscribe.get(i).equals(id)) {
                                        vSubBtn.setImageResource(R.drawable.ic_star_24dp);
                                        return;
                                    }
                                }
                            } catch (JSONException e) {
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
        }else{
            vSubBtn.setVisibility(View.GONE);
        }
    }

    public void onClickSubscribe(String subscriber, String follower) throws JSONException {
        String url = "https://readershare.herokuapp.com/subscribe";

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("subscriber", subscriber);
        jsonBody.put("follower", follower);
        final String requestBody = jsonBody.toString();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response == "200"){
                            vProfileName.setText("wwwwwwwwwwwwww");
                            vSubBtn.setImageResource(R.drawable.ic_star_24dp);

                        }else {
                            vSubBtn.setImageResource(R.drawable.ic_star_black_18dp);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError err) {
                        Log.d("Error.Response", err.getMessage());
                    }
                }
        ) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);
    }
}
