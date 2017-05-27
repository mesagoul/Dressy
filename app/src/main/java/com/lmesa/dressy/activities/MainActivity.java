package com.lmesa.dressy.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.lmesa.dressy.R;
import com.lmesa.dressy.fragments.Community.FragmentCommunity;
import com.lmesa.dressy.helpers.MatchDialog;
import com.lmesa.dressy.helpers.Drawer;
import com.lmesa.dressy.models.Clothes;

/**
 * Created by mac13 on 03/04/2017.
 */

public class MainActivity extends FragmentActivity{
    private Activity activity;
    private Toolbar toolbar;
    private Drawer drawer;
    private FloatingActionButton btn_match;
    private Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_match = (FloatingActionButton) findViewById(R.id.btn_match);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        this.activity = this;
        gson = new Gson();
        setToolbarName(getResources().getString(R.string.app_name));

        btn_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatchDialog matchDialog = new MatchDialog(activity);
                matchDialog.loadDialog();
            }
        });

        loadNewFragment(new FragmentCommunity(), false, true);
        drawer = new Drawer(this,toolbar);
        drawer.loadDrawer();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                Clothes clothes =  gson.fromJson(data.getStringExtra("similarity"), Clothes.class);
                Intent toSimilarityResultActivity = new Intent(getApplicationContext(), ActivitySimilarityResult.class);
                toSimilarityResultActivity.putExtra("similarity", data.getStringExtra("similarity"));
                toSimilarityResultActivity.putExtra("currentClotheUrl", data.getStringExtra("currentClotheUrl"));
                startActivity(toSimilarityResultActivity);
            }
        }
    }

    public void setToolbarName(String name){
        this.toolbar.setTitle(name);
    }

    public void onBackPressed() {
        if(drawer != null && drawer.isOpen()){
            drawer.closeDrawer();
        }
    }

    // load new fragment to show in activity
    public void loadNewFragment(Fragment newFragment , boolean isCloseFragment, boolean isFirstFragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout, newFragment);
        if(!isFirstFragment){
            if(isCloseFragment){
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            }else{
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            }
        }
        ft.addToBackStack(null);
        ft.commit();
    }
}
