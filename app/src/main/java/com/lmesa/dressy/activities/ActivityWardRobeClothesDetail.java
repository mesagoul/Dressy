package com.lmesa.dressy.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lmesa.dressy.R;
import com.lmesa.dressy.adapters.AdapterWardRobeClothes;
import com.lmesa.dressy.interfaces.WardRobeListener;
import com.lmesa.dressy.models.Clothes;

import java.util.ArrayList;

/**
 * Created by mac13 on 10/04/2017.
 */

public class ActivityWardRobeClothesDetail  extends AppCompatActivity implements WardRobeListener{
    private Clothes clothes;
    private Gson gson;
    private GridView gridView;
    private ImageView image;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardrobe_clothes_detail);
        gridView = (GridView) findViewById(R.id.wardrobe_clothes_detail_clothe_list);
        image = (ImageView) findViewById(R.id.wardrobe_clothes_detail_image);
        gson = new Gson();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        clothes = gson.fromJson(getIntent().getStringExtra("clothes"), Clothes.class);
        Glide
                .with(getApplicationContext())
                .load(clothes.getUrlImage())
                .centerCrop()
                .crossFade()
                .into(image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ActivityImageFullScreen.class);
                i.putExtra("image", clothes.getUrlImage());
                startActivity(i);
            }
        });

        AdapterWardRobeClothes adapterWardRobeClothes = new AdapterWardRobeClothes(this,clothes.getListClothe());
        adapterWardRobeClothes.setListener(this);
        gridView.setAdapter(adapterWardRobeClothes);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadDetail(int position) {
        Intent i = new Intent(getApplicationContext(), ActivityWardRobeClotheDetail.class);
        i.putExtra("clothe", gson.toJson(clothes.getListClothe().get(position)));
        startActivity(i);
    }

    @Override
    public boolean onLongClick(int position) {
        return false;
    }
}
