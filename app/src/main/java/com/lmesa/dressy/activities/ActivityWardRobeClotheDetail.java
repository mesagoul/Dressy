package com.lmesa.dressy.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lmesa.dressy.R;
import com.lmesa.dressy.models.Clothe;

/**
 * Created by Lucas on 09/04/2017.
 */

public class ActivityWardRobeClotheDetail  extends AppCompatActivity{
    private TextView name;
    private TextView color;
    private TextView reference;
    private TextView category;
    private TextView brand;
    private TextView material;
    private ImageView image;
    private Gson gson;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardrobe_clothe_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        name = (TextView) findViewById(R.id.wardrobe_clothe_detail_name);
        color = (TextView) findViewById(R.id.wardrobe_clothe_detail_color);
        reference = (TextView) findViewById(R.id.wardrobe_clothe_detail_reference);
        category = (TextView) findViewById(R.id.wardrobe_clothe_detail_type);
        brand = (TextView) findViewById(R.id.wardrobe_clothe_detail_brand);
        material = (TextView) findViewById(R.id.wardrobe_clothe_detail_material);
        image = (ImageView) findViewById(R.id.wardrobe_clothe_detail_image);
        gson = new Gson();

        final Clothe clothe = gson.fromJson(getIntent().getStringExtra("clothe"), Clothe.class);
        name.setText(clothe.getCloth_name());
        color.setText(clothe.getCloth_color());
        reference.setText(clothe.getCloth_reference());
        category.setText(clothe.getCloth_category());
        brand.setText(clothe.getCloth_brand());
        material.setText(clothe.getCloth_material());
        Glide
                .with(getApplicationContext())
                .load(clothe.getCloth_urlImage())
                .centerCrop()
                .crossFade()
                .into(image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ActivityImageFullScreen.class);
                i.putExtra("image",clothe.getCloth_urlImage());
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
