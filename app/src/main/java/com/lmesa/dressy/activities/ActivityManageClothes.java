package com.lmesa.dressy.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lmesa.dressy.R;
import com.lmesa.dressy.adapters.AdapterWardRobeClothes;
import com.lmesa.dressy.helpers.ResponseHttp;
import com.lmesa.dressy.interfaces.ServiceListener;
import com.lmesa.dressy.interfaces.WardRobeListener;
import com.lmesa.dressy.models.Clothe.Clothe;
import com.lmesa.dressy.models.Clothe.ClotheProperties;
import com.lmesa.dressy.models.Clothes;
import com.lmesa.dressy.network.ApiDressy;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by Lucas on 17/04/2017.
 */

public class ActivityManageClothes extends AppCompatActivity implements ServiceListener, WardRobeListener {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 55;
    private static final int CODE_ADD_CLOTHE = 831;
    private ImageView image;
    private GridView gridView;
    private Button btn_save;
    private Button btn_add;

    private Bitmap imageBitmap;

    private ArrayList<Clothe> listClothe;
    private Gson gson;
    private ApiDressy apiDressy;

    private ProgressBar progressBar;
    private LinearLayout content;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setContentView(R.layout.activity_manage_clothes);

        gson = new Gson();
        apiDressy = new ApiDressy(this);
        apiDressy.setListener(this);

        image = (ImageView) findViewById(R.id.manage_image);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        gridView = (GridView) findViewById(R.id.manage_clothes_list);
        btn_save = (Button) findViewById(R.id.manage_btn_save);
        btn_add = (Button) findViewById(R.id.manage_btn_add);
        content = (LinearLayout) findViewById(R.id.view_content);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toClotheListActivity = new Intent(getApplicationContext(),ActivityWardRobeClotheList.class);
                toClotheListActivity.putExtra("listClothe","true");
                startActivityForResult(toClotheListActivity,  CODE_ADD_CLOTHE );
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                content.setVisibility(View.GONE);
                Clothes clothes = new Clothes(imageToString(image),listClothe);
                if(isCreate()){
                    apiDressy.addClothes(clothes);
                }else if(isManage()){
                    apiDressy.manageClothes(clothes);
                }
            }
        });
        if(isCreate()){
            listClothe = new ArrayList<Clothe>();
            Intent toCameraActivity = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(toCameraActivity,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }else if(isManage()){
            Clothes clothes = gson.fromJson(getIntent().getStringExtra("clothes"), Clothes.class);
            listClothe = clothes.getListClothe();
            btn_save.setText(getResources().getString(R.string.manage));
            Glide
                    .with(getApplicationContext())
                    .load(clothes.getUrlImage())
                    .centerCrop()
                    .crossFade()
                    .into(image);


            loadGridView();
        }
    }

    public String imageToString(ImageView image){
        image.buildDrawingCache();
        Bitmap bmap = image.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return  Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    public void loadGridView(){
        AdapterWardRobeClothes adapterWardRobeClothes = new AdapterWardRobeClothes(this,listClothe);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODE_ADD_CLOTHE && resultCode == RESULT_OK){
            Clothe aClothe = gson.fromJson(data.getStringExtra("clothe"), Clothe.class);
            this.listClothe.add(aClothe);
            loadGridView();
        }else if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            this.imageBitmap = (Bitmap) extras.get("data");
            image.setImageBitmap(imageBitmap);

        }
    }

    @Override
    public void onGetUser(boolean isSucces) {

    }

    @Override
    public void onCreateUser(boolean isSucces) {

    }

    @Override
    public void onGetClothe(boolean isSucces, ArrayList<Clothe> listClothe) {

    }

    @Override
    public void onCreateClothe(boolean isSucces) {

    }

    @Override
    public void onDeleteClothe(boolean isSucces) {

    }

    @Override
    public void onManageClothes(boolean isSucces) {
        progressBar.setVisibility(View.GONE);
        content.setVisibility(View.VISIBLE);
        if(isSucces){
            Toast.makeText(getApplicationContext(), "Test MANAGE CLOTHES",Toast.LENGTH_SHORT).show();
            finish();
        }else{
            new ResponseHttp(getApplicationContext()).onErrorManageClothes();
            finish();

        }
    }

    @Override
    public void onGetClothes(boolean isSucces, ArrayList<Clothes> clothes) {

    }

    @Override
    public void onCreateClothes(boolean isSucces) {
        progressBar.setVisibility(View.GONE);
        content.setVisibility(View.VISIBLE);
        if(isSucces){
            Toast.makeText(getApplicationContext(), "Test ADD CLOTHES",Toast.LENGTH_SHORT).show();
            finish();
        }else{
            new ResponseHttp(getApplicationContext()).onErrorCreateClothes();
            finish();
        }
    }

    @Override
    public void onDeleteClothes(boolean isSucces) {
    }

    @Override
    public void onManageClothe(boolean isSucces) {

    }

    @Override
    public void onGetSimilarity(boolean isSucces) {

    }

    @Override
    public void onCreatePost(boolean isSucces) {

    }

    @Override
    public void onGetClotheProperties(boolean isSuccess, ClotheProperties clotheProperties) {

    }

    @Override
    public void loadDetail(int position) {
    }

    @Override
    public boolean onLongClick(int position) {
        return false;
    }

    public boolean isCreate() {
        return getIntent().getStringExtra("create") != null;
    }

    public boolean isManage() {
        return getIntent().getStringExtra("manage") != null;
    }
}

