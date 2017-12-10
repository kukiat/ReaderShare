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

import com.squareup.picasso.Picasso;

import org.w3c.dom.Comment;

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
        holder.vComentCreatedAt.setText(String.valueOf(commentItem.getCommentTimestamp()));
        Picasso.with(context).load(commentItem.getCommentUserImage()).into(holder.vCommentUserImage);
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
