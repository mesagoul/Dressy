package com.lmesa.dressy.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lmesa.dressy.R;
import com.lmesa.dressy.adapters.AdapterWardRobeClothes;
import com.lmesa.dressy.helpers.ResponseHttp;
import com.lmesa.dressy.interfaces.ServiceListener;
import com.lmesa.dressy.interfaces.WardRobeListener;
import com.lmesa.dressy.models.Clothe;
import com.lmesa.dressy.models.User;
import com.lmesa.dressy.network.ApiDressy;
import com.lmesa.dressy.network.Service;

import java.util.ArrayList;

/**
 * Created by Lucas on 09/04/2017.
 */

public class ActivityWardRobeClotheList extends AppCompatActivity implements WardRobeListener, ServiceListener {
    private GridView gridView;
    private ArrayList<Clothe> listClothe;
    private ProgressBar progressBar;
    private Gson gson;
    private ApiDressy apiDressy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardrobe_clothe);
        gridView = (GridView) findViewById(R.id.wardrobe_clothes_list);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        listClothe = new ArrayList<Clothe>();
        apiDressy = new ApiDressy(this);
        apiDressy.setListener(this);
        gson = new Gson();


        // banana
        Clothe clothe = new Clothe(
                "Name",
                "vert",
                "noReference",
                "http://blzjeans.com/8831-29091-thickbox/tee-shirt-vert-homme-tendance-et-fashion-lenny-and-loyd.jpg",
                "category",
                "brand",
                "material"
        );

        Clothe clothe2 = new Clothe(
                "Name",
                "Jaune",
                "noReference",
                "http://i2.cdscdn.com/pdt2/1/4/1/1/300x300/mp02972141/rw/pantalon-elastique-zip-couleur-pure-femme-jaune.jpg",
                "category",
                "brand",
                "material"
        );
        Clothe clothe3 = new Clothe(
                "Name",
                "Jaune",
                "noReference",
                "http://media.meltystyle.fr/article-1645763-ajust_930-f1374762253/asos-bermuda-chino-29-16-eur.jpg",
                "category",
                "brand",
                "material"
        );

        for(int i = 0; i <= 15 ; i++){
            listClothe.add(clothe);
            listClothe.add(clothe2);
            listClothe.add(clothe3);
            listClothe.add(clothe2);
            listClothe.add(clothe);
        }
        // banana


        AdapterWardRobeClothes adapterWardRobeClothes = new AdapterWardRobeClothes(this,listClothe);
        adapterWardRobeClothes.setListener(this);
        gridView.setAdapter(adapterWardRobeClothes);


    }
    public boolean isManageClothes(){
        return getIntent().getStringExtra("listClothe") != null;
    }

    @Override
    public void loadDetail(int position) {
        if(isManageClothes()){
            Intent resultIntent = new Intent();
            resultIntent.putExtra("clothe",gson.toJson(listClothe.get(position),Clothe.class));
            setResult(Activity.RESULT_OK,resultIntent);
            finish();
        }else {
            gridView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            apiDressy.getSimilarity(listClothe.get(position));
        }
    }

    @Override
    public boolean onLongClick(int position) {
        return false;
    }

    @Override
    public void onGetUser(boolean isSucces) {

    }

    @Override
    public void onCreateUser(boolean isSucces) {

    }

    @Override
    public void onGetClothe(boolean isSucces) {

    }

    @Override
    public void onCreateClothe(boolean isSucces) {

    }

    @Override
    public void onDeleteClothe(boolean isSucces) {

    }

    @Override
    public void onManageClothes(boolean isSucces) {
    }

    @Override
    public void onGetClothes(boolean isSucces) {

    }

    @Override
    public void onCreateClothes(boolean isSucces) {

    }

    @Override
    public void onDeleteClothes(boolean isSucces) {

    }

    @Override
    public void onManageClothe(boolean isSucces) {

    }

    @Override
    public void onGetSimilarity(boolean isSucces) {
        gridView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        if(isSucces){
            Toast.makeText(getApplicationContext(),"Test Similarity Clothe",Toast.LENGTH_SHORT).show();
            finish();
        }else{
            new ResponseHttp(getApplicationContext()).onErrorGetSimilarity();
            finish();
        }
    }

    @Override
    public void onCreatePost(boolean isSucces) {

    }
}
