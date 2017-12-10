package com.example.kukiat.readershare;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ocpsoft.pretty.time.PrettyTime;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Comment;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by aiy on 12/11/17.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<CommentItem> commentItems;
    private Context context;

    public CommentAdapter(List<CommentItem> commentItems, Context context) {
        this.commentItems = commentItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_comment, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CommentItem commentItem = commentItems.get(position);

        holder.vCommentContent.setText(commentItem.getCommentContent());
        holder.vCommentUserName.setText(commentItem.getCommentName());
        holder.vComentCreatedAt.setText(getDate(commentItem.getCommentTimestamp()));
        Picasso.with(context).load(commentItem.getCommentUserImage()).into(holder.vCommentUserImage);
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
        return commentItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ConstraintLayout vListCard;
        public TextView vCommentContent;
        public CircleImageView vCommentUserImage;
        public TextView vCommentUserName;
        public TextView vComentCreatedAt;

        public ViewHolder(View v) {
            super(v);
            vListCard = v.findViewById(R.id.comment_listcard);
            vCommentContent = v.findViewById(R.id.comment_content);
            vCommentUserImage = v.findViewById(R.id.comment_image);
            vCommentUserName = v.findViewById(R.id.comment_user_name);
            vComentCreatedAt = v.findViewById(R.id.comment_create_at);
        }
    }
}
