package com.lmesa.dressy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lmesa.dressy.R;
import com.lmesa.dressy.adapters.AdapterClotheSimilarity;
import com.lmesa.dressy.interfaces.SimilarityListener;
import com.lmesa.dressy.models.Clothe.Clothe;
import com.lmesa.dressy.models.Clothes;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


/**
 * Created by Lucas on 27/05/2017.
 */

public class ActivitySimilarityResult extends AppCompatActivity implements SimilarityListener {
    private Gson gson;
    private String currentClotheUrl;
    private Clothes clothesSimilarity;

    private ImageView image;
    private RecyclerView rvListClothes;
    private RecyclerView rvListClothesPartner;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similarity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        gson = new Gson();
        rvListClothes = (RecyclerView) findViewById(R.id.recycleViewSimilarity);
        rvListClothesPartner = (RecyclerView) findViewById(R.id.recycleViewSimilarityPartner);
        image = (ImageView) findViewById(R.id.similarity_detail_image);


        clothesSimilarity = gson.fromJson(getIntent().getStringExtra("similarity"), Clothes.class);
        currentClotheUrl = gson.fromJson(getIntent().getStringExtra("currentClotheUrl"), String.class);

        AdapterClotheSimilarity adapterClotheSimilarity = new AdapterClotheSimilarity(getApplicationContext(),clothesSimilarity.getListClothe(), false);
        adapterClotheSimilarity.setListener(this);
        rvListClothes.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false));
        rvListClothes.setAdapter(adapterClotheSimilarity);

        // set background to image
        Glide
                .with(getApplicationContext())
                .load(currentClotheUrl)
                .centerCrop()
                .crossFade()
                .into(image);




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onItemClick(Clothe clothe) {
        Intent toDetailAtctivity = new Intent(getApplicationContext(), ActivityWardRobeClotheDetail.class);
        toDetailAtctivity.putExtra("clothe", gson.toJson(clothe));
        startActivity(toDetailAtctivity);
        return false;
    }
}
