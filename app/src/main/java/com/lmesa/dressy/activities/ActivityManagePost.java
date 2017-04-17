package com.lmesa.dressy.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lmesa.dressy.R;
import com.lmesa.dressy.interfaces.ServiceListener;
import com.lmesa.dressy.models.Clothe;
import com.lmesa.dressy.models.Clothes;
import com.lmesa.dressy.models.Post;
import com.lmesa.dressy.network.ApiDressy;

import static com.lmesa.dressy.R.id.manage_desc;

/**
 * Created by Lucas on 17/04/2017.
 */

public class ActivityManagePost extends AppCompatActivity implements ServiceListener {

    private EditText title;
    private EditText desc;
    private Button btn_save;
    private ImageView image;
    private ApiDressy apiDressy;
    private Clothes clothes;
    private Gson gson;
    private ScrollView content;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_post);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        image = (ImageView) findViewById(R.id.manage_image);
        title = (EditText) findViewById(R.id.manage_title);
        desc = (EditText) findViewById(R.id.manage_desc);
        btn_save = (Button) findViewById(R.id.manage_button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        content = (ScrollView) findViewById(R.id.view_content);
        gson = new Gson();

        clothes = gson.fromJson(getIntent().getStringExtra("clothes"), Clothes.class);

        apiDressy = new ApiDressy(this);
        apiDressy.setListener(this);

        Glide
                .with(getApplicationContext())
                .load(clothes.getUrlImage())
                .centerCrop()
                .crossFade()
                .into(image);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                content.setVisibility(View.GONE);
                Post post = new Post(title.getText().toString(), desc.getText().toString(), clothes);
                apiDressy.createPost(post);

            }
        });
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

    }

    @Override
    public void onGetSimilarity() {

    }

    @Override
    public void onCreatePost() {
        progressBar.setVisibility(View.VISIBLE);
        content.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), "TEST ADD POST",Toast.LENGTH_SHORT).show();
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
