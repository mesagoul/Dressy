package com.lmesa.dressy.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.lmesa.dressy.R;
import com.lmesa.dressy.fragments.FragmentCommunityNews;
import com.lmesa.dressy.fragments.FragmentPagerView;
import com.lmesa.dressy.fragments.FragmentSignIn;
import com.lmesa.dressy.interfaces.PagerViewListener;

import java.util.ArrayList;

/**
 * Created by mac13 on 03/04/2017.
 */

public class MainActivity extends FragmentActivity implements PagerViewListener{
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setToolbarName(getResources().getString(R.string.app_name));
        loadFragmentViewPager();

    }
    public void setToolbarName(String name){
        this.toolbar.setTitle(name);
    }

    public void onBackPressed() {}

    @Override
    public ArrayList<Fragment> initFragmentsForPagerView(ArrayList<Fragment> listFragments) {
        FragmentCommunityNews fragmentCommunityNews = new FragmentCommunityNews();
        FragmentSignIn sign2 = new FragmentSignIn();
        listFragments.add(fragmentCommunityNews);
        listFragments.add(sign2);
        return listFragments;
    }

    @Override
    public void loadFragmentViewPager() {
        FragmentPagerView fragment = new FragmentPagerView();
        fragment.setListener(this);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_pager_view, fragment);
        ft.commit();
    }

    @Override
    public String[] initTitlesForPagerView() {
        return new String[]{getResources().getString(R.string.comm_news_title),getResources().getString(R.string.comm_leader_title)};
    }
}
