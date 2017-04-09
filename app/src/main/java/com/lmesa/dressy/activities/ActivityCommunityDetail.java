package com.lmesa.dressy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lmesa.dressy.R;
import com.lmesa.dressy.adapters.ImageAdapter;
import com.lmesa.dressy.models.Post;

/**
 * Created by Lucas on 08/04/2017.
 */

public class ActivityCommunityDetail extends AppCompatActivity {

    private TextView title;
    private TextView username;
    private ImageView image;
    private TextView desc;
    private TextView score;
    private Gson gson;
    private GridView gridview;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_detail);
        title = (TextView) findViewById(R.id.community_detail_title);
        username = (TextView) findViewById(R.id.community_detail_user);
        image = (ImageView) findViewById(R.id.community_detail_image);
        desc = (TextView) findViewById(R.id.community_detail_desc);
        score = (TextView) findViewById(R.id.community_detail_score);

        gridview = (GridView) findViewById(R.id.community_detail_clothe_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        gson = new Gson();
        final Post aPost = gson.fromJson(getIntent().getStringExtra("post"), Post.class);
        title.setText(aPost.getTitle());
        username.setText(aPost.getUsername());
        desc.setText(aPost.getDesc());
        score.setText(aPost.getScore().toString());

        gridview.setAdapter(new ImageAdapter(this, aPost.getClothes().getListClothe()));

        Glide
                .with(getApplicationContext())
                .load(aPost.getClothes().getUrlImage())
                .centerCrop()
                .crossFade()
                .into(image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ActivityImageFullScreen.class);
                i.putExtra("image",aPost.getClothes().getUrlImage());
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
