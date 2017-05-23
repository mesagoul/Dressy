package com.lmesa.dressy.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lmesa.dressy.R;
import com.lmesa.dressy.adapters.AdapterWardRobeClothes;
import com.lmesa.dressy.helpers.ResponseHttp;
import com.lmesa.dressy.interfaces.ServiceListener;
import com.lmesa.dressy.interfaces.WardRobeListener;
import com.lmesa.dressy.models.Clothe.Clothe;
import com.lmesa.dressy.models.Clothe.ClotheProperties;
import com.lmesa.dressy.models.Clothes;
import com.lmesa.dressy.models.Post;
import com.lmesa.dressy.network.ApiDressy;

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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        gridView = (GridView) findViewById(R.id.wardrobe_clothes_list);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        listClothe = new ArrayList<Clothe>();
        apiDressy = new ApiDressy(this);
        apiDressy.setListener(this);
        gson = new Gson();

        apiDressy.getClothe();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadAdapter(){
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
    public void onGetClothe(boolean isSucces, ArrayList<Clothe> listClothe) {
        if(isSucces){
            this.listClothe = listClothe;
            loadAdapter();

        }else{
            new ResponseHttp(getApplicationContext()).onErrorGetClothe();
        }

    }

    @Override
    public void onCreateClothe(boolean isSucces, int cloth_id) {

    }

    @Override
    public void onDeleteClothe(boolean isSucces) {

    }

    @Override
    public void onManageClothes(boolean isSucces) {
    }

    @Override
    public void onGetClothes(boolean isSucces, ArrayList<Clothes> clothes) {

    }

    @Override
    public void onCreateClothes(boolean isSucces, int id) {

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

    @Override
    public void onGetClotheProperties(boolean isSuccess, ClotheProperties clotheProperties) {

    }

    @Override
    public void onGetTopPosts(boolean isSuccess, ArrayList<Post> listPost) {

    }

    @Override
    public void onGetLastPosts(boolean isSuccess, ArrayList<Post> listPost) {

    }

    @Override
    public void onAddImage(boolean isSuccess, String urlImage) {

    }
}
