package com.lmesa.dressy.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.lmesa.dressy.R;
import com.lmesa.dressy.fragments.Community.FragmentCommunity;
import com.lmesa.dressy.helpers.CustomDialog;
import com.lmesa.dressy.helpers.Drawer;

/**
 * Created by mac13 on 03/04/2017.
 */

public class MainActivity extends FragmentActivity{
    private Activity activity;
    private Toolbar toolbar;
    private Drawer drawer;
    private FloatingActionButton btn_match;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_match = (FloatingActionButton) findViewById(R.id.btn_match);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        this.activity = this;
        setToolbarName(getResources().getString(R.string.app_name));

        btn_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Log.d("eee","Click button Floattant");
                CustomDialog customDialog = new CustomDialog(activity);
                customDialog.loadDialog();
            }
        });

        loadNewFragment(new FragmentCommunity(), false, true);
        drawer = new Drawer(this,toolbar);
        drawer.loadDrawer();

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
