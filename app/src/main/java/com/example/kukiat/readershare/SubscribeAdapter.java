package com.example.kukiat.readershare;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by aiy on 12/11/17.
 */

public class SubscribeAdapter extends RecyclerView.Adapter<SubscribeAdapter.ViewHolder>{
    private List<SubscribeItem> subscribeItems;
    private Context context;

    public SubscribeAdapter(List<SubscribeItem> subscribeItems, Context context) {
        this.subscribeItems = subscribeItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_subscribe, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SubscribeItem subscribeItem = subscribeItems.get(position);
        if(subscribeItem.getSubscribeName().isEmpty()){
            holder.vUsername.setText(subscribeItem.getSubscribeEmail());
        }else{
            holder.vUsername.setText(subscribeItem.getSubscribeName());
        }
        if(subscribeItem.getSubscribeImage().isEmpty()){
            Picasso.with(context).load("https://www.sparklabs.com/forum/styles/comboot/theme/images/default_avatar.jpg").into(holder.vImage);
        }else{
            Picasso.with(context).load(subscribeItem.getSubscribeImage()).into(holder.vImage);
        }
    }

    @Override
    public int getItemCount() {
        return subscribeItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout vListCard;
        public CircleImageView vImage;
        public TextView vUsername;

        public ViewHolder(View v) {
            super(v);

            vListCard = v.findViewById(R.id.subscribe_listcard);
            vImage = v.findViewById(R.id.subscribe_image);
            vUsername = v.findViewById(R.id.subscribe_user_name);

        }
    }
}
