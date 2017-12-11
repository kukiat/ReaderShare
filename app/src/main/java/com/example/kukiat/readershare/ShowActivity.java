package com.example.kukiat.readershare;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kukiat on 11/6/2017 AD.
 */

public class ShowActivity extends AppCompatActivity {
    TextView vReviewTitleShow;
    TextView vReviewContentShow;
    TextView vReviewLikeShow;
    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    ImageView vReviewerImage;
    TextView vReviewerName;
    ImageView vBookImageShow;

    ProgressDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Bundle bundle = getIntent().getExtras();
        dialog = ProgressDialog.show(ShowActivity.this, "","Loading. Please wait...", true);
        if(bundle != null) {
            String reviewId = bundle.getString("reviewId");
            Log.i("reviewId", String.valueOf(reviewId));
            fetchGetFeedData(reviewId);
        }
    }

    public void fetchGetFeedData(String id) {
        recyclerView = (RecyclerView) findViewById(R.id.recycle_comment);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final List<CommentItem> listComment = new ArrayList<>();
        String url = "https://readershare.herokuapp.com/review/"+id;
        Log.i("response",url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        vReviewTitleShow = (TextView) findViewById(R.id.review_title_show);
                        vReviewContentShow = (TextView) findViewById(R.id.review_content_show);
                        vReviewLikeShow = (TextView) findViewById(R.id.review_like);
                        vBookImageShow = (ImageView) findViewById(R.id.review_image_show);
                        vReviewerName =(TextView) findViewById(R.id.reviewer_name_show);
                        vReviewerImage = (ImageView) findViewById(R.id.reviewer_image_show);
                        JSONObject result;
                        try {
                            result = response.getJSONObject("result");

                            JSONObject review = result.getJSONObject("review");
                            JSONObject reviewer = result.getJSONObject("reviewer");
                            JSONObject book = result.getJSONObject("book");
                            JSONArray comment = result.getJSONArray("comment");

                            String reviewTitle = review.getString("title");
                            String reviewContent = review.getString("content");
                            int reviewLike = review.getInt("like");
                            int reviewRating = review.getInt("rating");

                            String reviewerName = reviewer.getString("name");
                            String reviewerEmail = reviewer.getString("email");
                            String reviewerImage = reviewer.getString("image");
//
                            String bookImage = book.getString("image");
//                            String bookName = book.getString("name");
                            dialog.dismiss();
                            vReviewTitleShow.setText(reviewTitle);
                            vReviewContentShow.setText(reviewContent);
                            vReviewLikeShow.setText(String.valueOf(reviewLike));
                            if(reviewerName.isEmpty()) {
                                vReviewerName.setText(reviewerEmail);
                            }else {
                                vReviewerName.setText(reviewerName);
                            }
                            if(reviewerImage.isEmpty()){
                                Picasso.with(getApplicationContext()).load(R.drawable.default_review_image).into(vReviewerImage);
                            }else{
                                Picasso.with(getApplicationContext()).load(reviewerImage).into(vReviewerImage);
                            }
                            if(bookImage.isEmpty()){
                                Picasso.with(getApplicationContext()).load(R.drawable.default_book_image).into(vBookImageShow);
                            }else{
                                Picasso.with(getApplicationContext()).load(bookImage).into(vBookImageShow);
                            }

                            for(int i=0; i<comment.length(); i++){
                                JSONObject _comment = comment.getJSONObject(i);
                                String commentContent = _comment.getString("commentContent");
                                String commentId = _comment.getString("id");
                                JSONObject commenter = _comment.getJSONObject("commenter");

                                String commentName = commenter.getString("name");

                                CommentItem commentItem = new CommentItem(commentId,
                                        commentContent,
                                        commentName,
                                        "https://assets.mubi.com/images/cast_member/120177/image-original.jpg?1488253454",
                                        1231241);
                                listComment.add(commentItem);
                            }
                            adapter = new CommentAdapter(listComment, getApplicationContext());
                            recyclerView.setAdapter(adapter);


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
