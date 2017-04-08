package com.lmesa.dressy.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.lmesa.dressy.interfaces.PagerViewListener;
import com.lmesa.dressy.R;
import com.lmesa.dressy.fragments.FragmentPagerView;
import com.lmesa.dressy.fragments.Sign.FragmentSignIn;
import com.lmesa.dressy.fragments.Sign.FragmentSignUp;

import java.util.ArrayList;

/**
 * Created by Lucas on 03/04/2017.
 */

public class SignInUpActivity extends FragmentActivity implements PagerViewListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_sign_in_up);
        loadFragmentViewPager();
    }

    @Override
    public void loadFragmentViewPager() {
        FragmentPagerView fragment = new FragmentPagerView();
        fragment.setListener(this);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.commit();
    }

    @Override
    public ArrayList<Fragment> initFragmentsForPagerView(ArrayList<Fragment> listFragments) {
        FragmentSignIn fragmentSignIn = new FragmentSignIn();
        FragmentSignUp fragmentSignUp = new FragmentSignUp();
        listFragments.add(fragmentSignIn);
        listFragments.add(fragmentSignUp);
        return listFragments;
    }

    @Override
    public String[] initTitlesForPagerView() {
        return new String[] {getResources().getString(R.string.sign_in),getResources().getString(R.string.sign_up)};
    }
}
