package com.example.kukiat.readershare;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends ActionBarActivity {

    public static final String URL =
            "http://blog.teamtreehouse.com/api/get_recent_summary/";

    private ListView mListView;
    private CustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);

        fetchData();

    }

    private void fetchData() {
        String jsonString = "";

        Gson gson = new Gson();
        Blog blog = gson.fromJson(jsonString, Blog.class);
        List<Post> posts = blog.getPosts();


        mAdapter = new CustomAdapter(this, posts);
        mListView.setAdapter(mAdapter);
    }

}