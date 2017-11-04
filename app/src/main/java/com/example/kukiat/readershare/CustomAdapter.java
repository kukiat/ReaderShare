package com.example.kukiat.readershare;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by kukiat on 11/4/2017 AD.
 */

public class CustomAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Post> mPosts;
    private ViewHolder mViewHolder;

    private Bitmap mBitmap;
    private Post mPost;
    private Activity mActivity;

    public CustomAdapter(Activity activity, List<Post> posts) {
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPosts = posts;
        mActivity = activity;
    }

    @Override
    public int getCount() {
        return mPosts.size();
    }

    @Override
    public Object getItem(int position) {
        return mPosts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.post_item, parent, false);
            mViewHolder = new ViewHolder();
            mViewHolder.picBook = (ImageView) convertView.findViewById(R.id.picBook);
            mViewHolder.name = (TextView) convertView.findViewById(R.id.name);
            mViewHolder.rating = (TextView) convertView.findViewById(R.id.rating);
            mViewHolder.topic = (TextView) convertView.findViewById(R.id.topic);

            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mPost = mPosts.get(position);

        if (mPost.getPicBook() != null) {
            mViewHolder.picBook.setImageBitmap(mBitmap);
        }
        Picasso.with(mActivity)
                .load(mPost.getPicBook())
                .into(mViewHolder.picBook);

        mViewHolder.name.setText(mPost.name);
        mViewHolder.topic.setText(mPost.topic);
        mViewHolder.rating.setText(mPost.rating);

        return convertView;
    }

    private static class ViewHolder {
        ImageView picBook;
        TextView name;
        TextView topic;
        TextView rating;
    }
}