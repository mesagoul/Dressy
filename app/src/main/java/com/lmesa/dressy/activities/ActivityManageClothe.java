package com.lmesa.dressy.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
import com.lmesa.dressy.adapters.AdapterClotheProperties;
import com.lmesa.dressy.helpers.ResponseHttp;
import com.lmesa.dressy.interfaces.ServiceListener;
import com.lmesa.dressy.models.Clothe.Brand;
import com.lmesa.dressy.models.Clothe.Category;
import com.lmesa.dressy.models.Clothe.Clothe;
import com.lmesa.dressy.models.Clothe.ClotheProperties;
import com.lmesa.dressy.models.Clothe.Color;
import com.lmesa.dressy.models.Clothe.Material;
import com.lmesa.dressy.models.Clothes;
import com.lmesa.dressy.models.Post;
import com.lmesa.dressy.network.ApiDressy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Lucas on 15/04/2017.
 */

public class ActivityManageClothe extends AppCompatActivity implements ServiceListener{
    private ImageView image;
    private EditText nom;
    private EditText reference;
    private Button btn_save;
    private ApiDressy apiDressy;
    private ScrollView scrollView;
    private ProgressBar progressBar;
    private Bitmap imageBitmap;
    private Clothe editClothe;
    private Gson gson;
    private Spinner spinnerCategories;
    private Spinner spinnerColor;
    private Spinner spinnerBrands;
    private Spinner spinnerMaterials;
    private ArrayList<Brand> listBrands;
    private ArrayList<Material> listMaterials;
    private ArrayList<Category> listCategories;
    private ArrayList<Color> listColors;
    private Category currentCategory;
    private Material currentMaterial;
    private Brand currentBrand;
    private Color currentColor;
    private File  imageFile;

    private Clothe currentClothe;
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
        spinnerCategories = (Spinner) findViewById(R.id.clothe_create_categories);
        spinnerBrands = (Spinner) findViewById(R.id.clothe_create_brands);
        spinnerMaterials = (Spinner) findViewById(R.id.clothe_create_materials);
        spinnerColor = (Spinner) findViewById(R.id.clothe_create_color);

        image = (ImageView) findViewById(R.id.clothe_create_image);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);

        nom = (EditText) findViewById(R.id.clothe_create_name);
        reference = (EditText) findViewById(R.id.clothe_create_reference);
        btn_save = (Button) findViewById(R.id.btn_save);

        imageFile = null;

        apiDressy.getClotheProperties();


        if(isManage()){
            btn_save.setText(getResources().getString(R.string.edit));
            editClothe = gson.fromJson(getIntent().getStringExtra("editClothe"), Clothe.class);
            nom.setText(editClothe.getCloth_name());
            reference.setText(editClothe.getCloth_reference());

            Glide
                    .with(getApplicationContext())
                    .load(editClothe.getCloth_urlImage())
                    .centerCrop()
                    .crossFade()
                    .into(image);
        }else{
            Intent toCameraActivity = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (toCameraActivity.resolveActivity(getPackageManager()) != null) {
                try {
                    imageFile = createImageFile();
                } catch (IOException ex) {
                    Log.d("File","Error creating file ActivityManage l.125");
                }
                if (imageFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(this,
                            "com.example.android.fileprovider",
                            imageFile);
                    toCameraActivity.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(toCameraActivity, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                }
            }
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
                currentClothe = new Clothe(nom.getText().toString(),currentColor, reference.getText().toString(), imageFile.getName(),currentBrand,currentMaterial,currentCategory);
                if(isMatch()){
                    apiDressy.getSimilarity(currentClothe);
                }else if(isManage()){
                    currentClothe.setCloth_id(editClothe.getCloth_id());
                    apiDressy.manageClothe(currentClothe);
                }else{
                    apiDressy.addClothe(currentClothe);
                    if(imageFile != null){
                        apiDressy.addImageClothe(imageFile, imageFile.getName());
                    }
                }
            }
        });
    }




    public void loadSpinners(){
        AdapterClotheProperties adapterCategory = new AdapterClotheProperties(getApplicationContext(),this.listCategories);
        spinnerCategories.setAdapter(adapterCategory);

        AdapterClotheProperties adapterMaterials = new AdapterClotheProperties(getApplicationContext(),this.listMaterials);
        spinnerMaterials.setAdapter(adapterMaterials);

        AdapterClotheProperties adapterBrand = new AdapterClotheProperties(getApplicationContext(),this.listBrands);
        spinnerBrands.setAdapter(adapterBrand);

        AdapterClotheProperties adapterColors = new AdapterClotheProperties(getApplicationContext(),this.listColors);
        spinnerColor.setAdapter(adapterColors);

        this.loadListeners();
    }


    public void loadListeners(){

        if(isManage()){
            spinnerCategories.setSelection(this.listCategories.indexOf(editClothe.getCloth_category()));
            spinnerMaterials.setSelection(this.listMaterials.indexOf(editClothe.getCloth_material()));
            spinnerBrands.setSelection(this.listBrands.indexOf(editClothe.getCloth_brand()));
            spinnerColor.setSelection(this.listColors.indexOf(editClothe.getCloth_color()));
        }
        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentCategory = listCategories.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerBrands.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentBrand = listBrands.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerMaterials.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentMaterial = listMaterials.get(position);
                Log.d("MATERIAL",currentMaterial.getLibelle());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentColor = listColors.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                Bitmap bitmap = BitmapFactory.decodeFile(this.imageFile.getPath());
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2,bitmap.getHeight()/2,false);
                image.setImageBitmap(resizedBitmap);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        imageFile = image;
        return image;
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
    public void onCreateClothe(boolean isSucces, int cloth_id) {
        scrollView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        if(isSucces){
            this.currentClothe.setCloth_id(cloth_id);
            Intent response = new Intent();
            response.putExtra("clothe", gson.toJson(this.currentClothe));
            setResult(RESULT_OK,response);
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
    public void onCreateClothes(boolean isSucces, int id) {

    }

    @Override
    public void onDeleteClothes(boolean isSucces) {

    }

    @Override
    public void onManageClothe(boolean isSucces) {
        scrollView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        if(isSucces){
            Intent response = new Intent();
            response.putExtra("clothe", gson.toJson(currentClothe));
            setResult(RESULT_OK,response);
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

    @Override
    public void onGetClotheProperties(boolean isSuccess, ClotheProperties clotheProperties) {
        if(isSuccess){
            this.listBrands = clotheProperties.getListBrands();
            this.listCategories = clotheProperties.getListCategories();
            this.listMaterials = clotheProperties.getListMaterials();
            this.listColors = clotheProperties.getListColors();

            loadSpinners();

        }else{

            new ResponseHttp(getApplicationContext()).onErrorGetPropertiesClothe();
        }

    }

    @Override
    public void onGetTopPosts(boolean isSuccess, ArrayList<Post> listPost) {

    }

    @Override
    public void onGetLastPosts(boolean isSuccess, ArrayList<Post> listPost) {

    }

    @Override
    public void onAddImage(boolean b) {

    }
}
