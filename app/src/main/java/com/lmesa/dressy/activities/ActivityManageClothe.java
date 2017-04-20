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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lmesa.dressy.R;
import com.lmesa.dressy.helpers.ResponseHttp;
import com.lmesa.dressy.interfaces.ServiceListener;
import com.lmesa.dressy.models.Clothe.Clothe;
import com.lmesa.dressy.models.Clothes;
import com.lmesa.dressy.network.ApiDressy;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by Lucas on 15/04/2017.
 */

public class ActivityManageClothe extends AppCompatActivity implements ServiceListener{
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
    private Spinner categories;

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
        categories = (Spinner) findViewById(R.id.clothe_create_categories);

        image = (ImageView) findViewById(R.id.clothe_create_image);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);

        nom = (EditText) findViewById(R.id.clothe_create_name);
        color = (EditText) findViewById(R.id.clothe_create_color);
        reference = (EditText) findViewById(R.id.clothe_create_reference);
        category = (EditText) findViewById(R.id.clothe_create_category);
        brand = (EditText) findViewById(R.id.clothe_create_brand);
        material = (EditText) findViewById(R.id.clothe_create_material);
        btn_save = (Button) findViewById(R.id.btn_save);


        if(isManage()){
            btn_save.setText(getResources().getString(R.string.edit));
            editClothe = gson.fromJson(getIntent().getStringExtra("editClothe"), Clothe.class);
            nom.setText(editClothe.getCloth_name());
            color.setText(editClothe.getCloth_color());
            reference.setText(editClothe.getCloth_reference());
            category.setText(editClothe.getCloth_category().getLibelle());
            brand.setText(editClothe.getCloth_brand().getLibelle());
            material.setText(editClothe.getCloth_material().getLibelle());

            Glide
                    .with(getApplicationContext())
                    .load(editClothe.getCloth_urlImage())
                    .centerCrop()
                    .crossFade()
                    .into(image);
        }else{
            loadSpinner();
            Intent toCameraActivity = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(toCameraActivity,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }

        if(isMatch()){
            btn_save.setText(getResources().getString(R.string.find_match));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                //Clothe clothe = new Clothe(nom.getText().toString(), color.getText().toString(), reference.getText().toString(), imageToString(image),category.getText().toString(),brand.getText().toString(),material.getText().toString());
                if(isMatch()){
                    //apiDressy.getSimilarity(clothe);
                }else if(isManage()){
                   // apiDressy.manageClothe(clothe);
                }else{
                    //apiDressy.addClothe(clothe);
                }

            }
        });
    }

    public void loadSpinner(){
        ArrayList<String> list = new ArrayList<>();
        list.add("Ello");
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,list);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        categories.setAdapter(adapter);
    }

    public String imageToString(ImageView image){
        image.buildDrawingCache();
        Bitmap bmap = image.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return  Base64.encodeToString(imageBytes, Base64.DEFAULT);
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
    public boolean isCreate(){ return getIntent().getStringExtra("create") != null; }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
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
        scrollView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        if(isSucces){
            Toast.makeText(getApplicationContext(),"Test Add Clothe",Toast.LENGTH_SHORT).show();
            finish();
        }else{
            new ResponseHttp(getApplicationContext()).onErrorCreateClothe();
            finish();

        }
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
    public void onCreateClothes(boolean isSucces) {

    }

    @Override
    public void onDeleteClothes(boolean isSucces) {

    }

    @Override
    public void onManageClothe(boolean isSucces) {
        scrollView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        if(isSucces){
            Toast.makeText(getApplicationContext(),"Test Edit Clothe",Toast.LENGTH_SHORT).show();
            finish();
        }else{
            new ResponseHttp(getApplicationContext()).onErrorManageClothe();
            finish();

        }

    }

    @Override
    public void onGetSimilarity(boolean isSucces) {
        scrollView.setVisibility(View.VISIBLE);
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
