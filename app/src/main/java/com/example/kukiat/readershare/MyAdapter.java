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

import java.util.List;

/**
 * Created by kukiat on 11/6/2017 AD.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ListItem listItem = listItems.get(position);

        holder.vTopic.setText(listItem.getTopic());
        holder.vName.setText(listItem.getName());
        holder.vRating.setText(listItem.getRating());
        Log.i("book",listItem.getBook().toString());
        Picasso.with(context).load(listItem.getBook()).resize(230, 200).into(holder.vBook);

        holder.vListCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowActivity.class);
                intent.putExtra("id", listItem.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView vName;
        public TextView vTopic;
        public TextView vRating;
        public ImageView vBook;
        public ConstraintLayout vListCard;

        public ViewHolder(View v) {
            super(v);
            vName = (TextView) v.findViewById(R.id.name);
            vRating = (TextView) v.findViewById(R.id.rating);
            vTopic = (TextView) v.findViewById(R.id.topic);
            vBook = (ImageView) v.findViewById(R.id.picName);
            vListCard = (ConstraintLayout) v.findViewById(R.id.listCard);
        }
    }
}
