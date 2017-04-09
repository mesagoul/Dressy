package com.lmesa.dressy.fragments.WardRobe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lmesa.dressy.R;
import com.lmesa.dressy.fragments.FragmentPagerView;
import com.lmesa.dressy.interfaces.PagerViewListener;

import java.util.ArrayList;

/**
 * Created by Lucas on 09/04/2017.
 */

public class FragmentWardRobe extends Fragment implements PagerViewListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_wardrobe,container,false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        loadFragmentViewPager();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public ArrayList<Fragment> initFragmentsForPagerView() {
        ArrayList<Fragment>  listFragments = new ArrayList<Fragment>();
        FragmentWardRobeClothes fragmentWardRobeClothes = new FragmentWardRobeClothes();
        FragmentWardRobeClothe fragmentWardRobeClothe = new FragmentWardRobeClothe();
        listFragments.add(fragmentWardRobeClothes);
        listFragments.add(fragmentWardRobeClothe);
        return listFragments;
    }

    @Override
    public void loadFragmentViewPager() {
        FragmentPagerView fragment = new FragmentPagerView();
        fragment.setListener(this);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_pager_view, fragment);
        ft.commit();
    }

    @Override
    public String[] initTitlesForPagerView() {
        return new String[]{getResources().getString(R.string.ward_clothes_title),getResources().getString(R.string.ward_clothe_title),};
    }
}
