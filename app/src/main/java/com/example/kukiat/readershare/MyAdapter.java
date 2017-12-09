package com.example.kukiat.readershare;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by kukiat on 11/6/2017 AD.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<ReviewItem> reviewItems;
    private Context context;

    public MyAdapter(List<ReviewItem> reviewItems, Context context) {
        this.reviewItems = reviewItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ReviewItem reviewItem = reviewItems.get(position);

        holder.vReviewTitle.setText(reviewItem.getReviewTitle());
        holder.vReviewContent.setText(reviewItem.getReviewContent().substring(0, 50)+"...");
        holder.vReviewRating.setText(String.valueOf(reviewItem.getReviewRating())+" /10");

    }

    @Override
    public int getItemCount() {
        return reviewItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ConstraintLayout vListCard;
        public TextView vReviewTitle;
        public TextView vReviewContent;
        public TextView vReviewRating;
        public CircleImageView vReviewerImage;
        public ImageView vReviewImage;
        public TextView vReviewerName;
        public TextView vCreatedAt;
        public ImageView vSubscribe;
        public ImageView vBookmark;

        public ViewHolder(View v) {
            super(v);
//            vListCard = (ConstraintLayout) v.findViewById(R.id.listCard);
            vReviewTitle = (TextView) v.findViewById(R.id.textView2);
            vReviewContent = (TextView) v.findViewById(R.id.topic);
            vReviewRating = (TextView) v.findViewById(R.id.textView);
//            vReviewerImage = (ImageView) v.findViewById();
//            vReviewImage = (ImageView) v.findViewById();
//            vReviewerName = (TextView) v.findViewById(R.id.listCard);
//            vCreatedAt = (TextView) v.findViewById(R.id.);
//            vSubscribe = (ImageView) v.findViewById();
//            vBookmark = (ImageView) v.findViewById();
        }
    }
}
