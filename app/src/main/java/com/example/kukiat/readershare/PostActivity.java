package com.example.kukiat.readershare;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kukiat on 11/13/2017 AD.
 */

public class PostActivity extends AppCompatActivity {

    private static int TAKE_PHOTO_REQUEST_CODE = 11;
    Uri outputFileUri;

    EditText vPostTitile;
    EditText vPostContent;
    EditText vPostRating;
    EditText vPostBook;
    Button vPostCreate;
    StorageReference storageReference;
    private FirebaseUser user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_layout);
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            onPrepareData();
        }else{

        }
    }

    public void onPrepareData()  {
        vPostTitile = (EditText) findViewById(R.id.post_topic);
        vPostContent = (EditText) findViewById(R.id.post_content);
        vPostRating = (EditText) findViewById(R.id.post_topic);
        vPostBook = (EditText) findViewById(R.id.post_bookname);
        vPostCreate = (Button) findViewById(R.id.post_create);



        vPostCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String postTitleData = vPostTitile.getText().toString();
                final String postContentData = vPostContent.getText().toString();
                String postRatingData = vPostRating.getText().toString();
                final String postBookData = vPostBook.getText().toString();

                try {
                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("reviewContent", postContentData);
                    jsonBody.put("reviewTitle", postTitleData);
                    jsonBody.put("bookName", postBookData);
                    jsonBody.put("uId", user.getUid());
                    String requestBody = jsonBody.toString();
                    Log.i("xxxxx",requestBody);

                    postReview(requestBody);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void postReview(final String requestBody) {
        String url = "http://10.0.2.2:3000/post";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("200")){
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                            Toast.makeText(PostActivity.this, "Post Review Success",
                                    Toast.LENGTH_SHORT).show();
                            finish();
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
