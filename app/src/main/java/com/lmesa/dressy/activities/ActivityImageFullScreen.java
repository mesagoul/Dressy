package com.lmesa.dressy.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lmesa.dressy.R;

/**
 * Created by Lucas on 09/04/2017.
 */

public class ActivityImageFullScreen extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_full_screen);
        imageView = (ImageView) findViewById(R.id.image_full_screen);
        String urlImage  = getIntent().getStringExtra("image");
        Glide
                .with(getApplicationContext())
                .load(urlImage)
                .centerCrop()
                .crossFade()
                .into(imageView);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
