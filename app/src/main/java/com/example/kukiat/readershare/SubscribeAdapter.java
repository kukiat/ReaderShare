package com.example.kukiat.readershare;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

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

    }

    @Override
    public int getItemCount() {
        return subscribeItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }
}
