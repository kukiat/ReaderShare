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
import android.widget.TextView;

import com.ocpsoft.pretty.time.PrettyTime;
import com.squareup.picasso.Picasso;

import java.sql.Timestamp;
import java.util.Date;
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
        holder.vReviewerName.setText(reviewItem.getReviewerEmail());
        holder.vCreatedAt.setText(getDate(reviewItem.getTimestamp()));
        holder.vReviewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowActivity.class);
                intent.putExtra("reviewId", reviewItem.getId());
                context.startActivity(intent);
            }
        });
        if(reviewItem.getBookImage().isEmpty()){
            Picasso.with(context).load(R.drawable.default_book_image).into(holder.vReviewImage);
        }else {
            Picasso.with(context).load(reviewItem.getBookImage()).into(holder.vReviewImage);
        }
        if(reviewItem.getReviewerName().isEmpty()) {
            holder.vReviewerName.setText(reviewItem.getReviewerEmail());
        }else {
            holder.vReviewerName.setText(reviewItem.getReviewerName());
        }
        if(reviewItem.getReviewerImage().isEmpty()){
            Picasso.with(context).load("https://www.sparklabs.com/forum/styles/comboot/theme/images/default_avatar.jpg").into(holder.vReviewerImage);
        }else{
            Picasso.with(context).load(reviewItem.getReviewerImage()).into(holder.vReviewerImage);
        }
//        holder.vBookmark.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("click","subscribe");
//            }
//        });

        holder.vReviewerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentProfile = new Intent(context, ProfileActivity.class);
                intentProfile.putExtra("id",reviewItem.getReviewerId());
                context.startActivity(intentProfile);
            }
        });
    }

    public String getDate(int timestamp) {
        Timestamp stamp = new Timestamp(timestamp);
        if(stamp.getTime() < 86400000){
            Date date = new Date(stamp.getTime());
            PrettyTime p = new PrettyTime();
            return p.format(date);
        }else{
            Date date = new Date(stamp.getTime());
            int year = date.getYear()+1947;
            int day = date.getDate()-2;
            int month = date.getMonth()+12==12?12:(date.getMonth()+12)%12;
            String data = String.valueOf(day)+"/"+String.valueOf(month)+"/"+ String.valueOf(year);
            return data;
        }
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
//        public ImageView vBookmark;

        public ViewHolder(View v) {
            super(v);
            vListCard = (ConstraintLayout) v.findViewById(R.id.listCard);
            vReviewTitle = (TextView) v.findViewById(R.id.review_title);
            vReviewContent = (TextView) v.findViewById(R.id.review_content);
            vReviewRating = (TextView) v.findViewById(R.id.review_rating);
            vReviewerImage = (CircleImageView) v.findViewById(R.id.reviewer_image);
            vReviewImage = (ImageView) v.findViewById(R.id.review_image);
            vReviewerName = (TextView) v.findViewById(R.id.reviewer_name_show);
            vCreatedAt = (TextView) v.findViewById(R.id.review_create_at);
//            vBookmark = (ImageView) v.findViewById(R.id.bookmark);
        }
    }
}
