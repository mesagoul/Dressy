package com.lmesa.dressy.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.lmesa.dressy.R;
import com.lmesa.dressy.animation.ZoomOutPageTransformer;

import java.util.ArrayList;

/**
 * Created by mac13 on 03/04/2017.
 */

public class FragmentPagerView extends Fragment {
    private ArrayList<Fragment> listFragments;
    // VIEW PAGER
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_pager_view, container,false);
        mPager = (ViewPager) v.findViewById(R.id.pager_view_pager);
        tabLayout = (TabLayout) v.findViewById(R.id.pager_view_tabs);
        listFragments = new ArrayList<Fragment>();

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragmentsForPagerView();
        mPager.setPageTransformer(true,new ZoomOutPageTransformer());
        mPagerAdapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager(), listFragments);
        mPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(mPager);
    }

    public void initFragmentsForPagerView(){
        FragmentSignIn fragmentSignIn = new FragmentSignIn();
        FragmentSignIn fragmentSignIn1 = new FragmentSignIn();
        listFragments.add(fragmentSignIn1);
        listFragments.add(fragmentSignIn);
    }


    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        private ArrayList<Fragment> listFragments;
        private String tabTitles[] = new String[] { "Sign In", "Sign Up" };

        public ScreenSlidePagerAdapter(FragmentManager fm, ArrayList<Fragment> listFragments) {
            super(fm);
            this.listFragments = listFragments;
        }

        @Override
        public Fragment getItem(int position) {
            return listFragments.get(position);
        }

        @Override
        public int getCount() {
            return listFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}


