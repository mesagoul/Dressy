package com.lmesa.dressy.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lmesa.dressy.R;
import com.lmesa.dressy.interfaces.ServiceListener;
import com.lmesa.dressy.models.Clothe;
import com.lmesa.dressy.network.ApiDressy;

/**
 * Created by Lucas on 15/04/2017.
 */

public class ActivityCreateClothe extends AppCompatActivity implements ServiceListener{
    private ImageView image;
    private EditText nom;
    private EditText color;
    private EditText reference;
    private EditText category;
    private EditText brand;
    private EditText material;
    private Button btn_save;
    private ApiDressy apiDressy;
    private ScrollView scrollView;
    private ProgressBar progressBar;
    private Bitmap imageBitmap;
    private Clothe editClothe;
    private Gson gson;

    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_clothe);
        gson = new Gson();
        apiDressy = new ApiDressy(this);
        apiDressy.setListener(this);
        scrollView = (ScrollView) findViewById(R.id.view_create_clothe);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        image = (ImageView) findViewById(R.id.clothe_create_image);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);

        nom = (EditText) findViewById(R.id.clothe_create_name);
        color = (EditText) findViewById(R.id.clothe_create_color);
        reference = (EditText) findViewById(R.id.clothe_create_reference);
        category = (EditText) findViewById(R.id.clothe_create_category);
        brand = (EditText) findViewById(R.id.clothe_create_brand);
        material = (EditText) findViewById(R.id.clothe_create_material);
        btn_save = (Button) findViewById(R.id.btn_save);
        if(isMatch()){
            btn_save.setText(getResources().getString(R.string.find_match));
        }else if(isManage()){
            btn_save.setText(getResources().getString(R.string.edit));
        }

        if(isManage()){
            editClothe = gson.fromJson(getIntent().getStringExtra("editClothe"), Clothe.class);
            nom.setText(editClothe.getCloth_name());
            color.setText(editClothe.getCloth_color());
            reference.setText(editClothe.getCloth_reference());
            category.setText(editClothe.getCloth_category());
            brand.setText(editClothe.getCloth_brand());
            material.setText(editClothe.getCloth_material());
            Glide
                    .with(getApplicationContext())
                    .load(editClothe.getCloth_urlImage())
                    .centerCrop()
                    .crossFade()
                    .into(image);
        }

        if(isMatch()){
            Intent toCameraActivity = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(toCameraActivity,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMatch()){
                    scrollView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    Clothe clothe = new Clothe(nom.getText().toString(), color.getText().toString(), reference.getText().toString(),"",category.getText().toString(),brand.getText().toString(),material.getText().toString());
                    apiDressy.getSimilarity(clothe);

                }else if(isManage()){
                    scrollView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    Clothe clothe = new Clothe(nom.getText().toString(), color.getText().toString(), reference.getText().toString(),"",category.getText().toString(),brand.getText().toString(),material.getText().toString());
                    apiDressy.manageClothe(clothe);

                }else{
                    scrollView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    Clothe clothe = new Clothe(nom.getText().toString(), color.getText().toString(), reference.getText().toString(),"",category.getText().toString(),brand.getText().toString(),material.getText().toString());
                    apiDressy.addClothe(clothe);
                }

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                this.imageBitmap = (Bitmap) extras.get("data");
                image.setImageBitmap(imageBitmap);}
        }
    }

    public boolean isMatch(){
        return getIntent().getStringExtra("match") != null;
    }
    public boolean isManage(){ return getIntent().getStringExtra("manage") != null; }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetUser() {

    }

    @Override
    public void onCreateUser() {

    }

    @Override
    public void onGetClothe() {

    }

    @Override
    public void onCreateClothe() {
        // DO SOMETHING
        scrollView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(),"Test Add Clothe",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onDeleteClothe() {

    }

    @Override
    public void onManageClothes() {

    }

    @Override
    public void onGetClothes() {

    }

    @Override
    public void onCreateClothes() {

    }

    @Override
    public void onDeleteClothes() {

    }

    @Override
    public void onManageClothe() {
        scrollView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(),"Test Edit Clothe",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onGetSimilarity() {
        scrollView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(),"Test Similarity Clothe",Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public void onCreatePost() {

    }
}
