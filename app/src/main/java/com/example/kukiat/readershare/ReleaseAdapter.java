package com.example.kukiat.readershare;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    }

    @Override
    public int getItemCount() {
        return releaseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ConstraintLayout vListCard;
        public ImageView vReleaseBookImage;
        public TextView vReleaeTopic;
        public TextView vReleaseCreatedAt;

        public ViewHolder(View v) {
            super(v);
            vListCard = v.findViewById(R.id.release_listcard);
            vReleaseBookImage = v.findViewById(R.id.release_book_image);
            vReleaeTopic = v.findViewById(R.id.releasse_topic);
            vReleaseCreatedAt = v.findViewById(R.id.release_create_at);
        }
    }
}
