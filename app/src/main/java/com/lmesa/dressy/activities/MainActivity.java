package com.lmesa.dressy.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.lmesa.dressy.R;
import com.lmesa.dressy.fragments.Community.FragmentCommunity;

/**
 * Created by mac13 on 03/04/2017.
 */

public class MainActivity extends FragmentActivity{
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setToolbarName(getResources().getString(R.string.app_name));
        loadNewFragment(new FragmentCommunity(), false, true);

    }
    public void setToolbarName(String name){
        this.toolbar.setTitle(name);
    }

    public void onBackPressed() {}



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
