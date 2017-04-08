package com.lmesa.dressy.interfaces;

import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * Created by Lucas on 08/04/2017.
 */

public interface PagerViewListener {
    ArrayList<Fragment> initFragmentsForPagerView(ArrayList<Fragment> listFragments);
    void loadFragmentViewPager();
    String[] initTitlesForPagerView();
}
