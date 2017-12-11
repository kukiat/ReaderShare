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
 * Created by aiy on 12/11/17.
 */

public class ReleaseAdapter extends RecyclerView.Adapter<ReleaseAdapter.ViewHolder> {
    private List<ReleaseItem> releaseItems;
    private Context context;

    public ReleaseAdapter(List<ReleaseItem> releaseItems, Context context) {
        this.releaseItems = releaseItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_release, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ReleaseItem releaseItem = releaseItems.get(position);

        holder.vReleaseTopic.setText(releaseItem.getReviewTitle());
        holder.vReleaseCreatedAt.setText(getDate(releaseItem.getTimestamp()));
        if(releaseItem.getBookImage().isEmpty()) {
            Picasso.with(context).load(R.drawable.images).into(holder.vReleaseBookImage);
        }else{
            Picasso.with(context).load(releaseItem.getBookImage()).into(holder.vReleaseBookImage);
        }
        Log.i("wdwdwdsx",releaseItem.getReviewerId());
        holder.vListCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentShow = new Intent(context, ShowActivity.class);
                intentShow.putExtra("reviewId", releaseItem.getId());
                context.startActivity(intentShow);
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
        return releaseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ConstraintLayout vListCard;
        public ImageView vReleaseBookImage;
        public TextView vReleaseTopic;
        public TextView vReleaseCreatedAt;

        public ViewHolder(View v) {
            super(v);
            vListCard = v.findViewById(R.id.release_listcard);
            vReleaseBookImage = v.findViewById(R.id.release_book_image);
            vReleaseTopic = v.findViewById(R.id.releasse_topic);
            vReleaseCreatedAt = v.findViewById(R.id.release_create_at);
        }
    }
}
