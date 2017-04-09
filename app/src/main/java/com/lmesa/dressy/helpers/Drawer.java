package com.lmesa.dressy.helpers;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lmesa.dressy.R;
import com.lmesa.dressy.activities.MainActivity;
import com.lmesa.dressy.fragments.Community.FragmentCommunity;
import com.lmesa.dressy.fragments.WardRobe.FragmentWardRobe;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

/**
 * Created by Lucas on 09/04/2017.
 */

public class Drawer{

    private com.mikepenz.materialdrawer.Drawer drawer;
    private Activity activity;
    private Toolbar toolbar;
    private PrimaryDrawerItem community_item;
    private PrimaryDrawerItem armory_item;

    public Drawer(Activity activity, Toolbar bar){
        this.activity = activity;
        this.toolbar = bar;

        armory_item = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.armory_title).withIcon(R.drawable.ic_armory);
        community_item = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.comm_title).withIcon(R.drawable.ic_comm);
    }

    public void  loadDrawer(){
        drawer = new DrawerBuilder()
                .withActivity(this.activity)
                .withToolbar(this.toolbar)
                .withActionBarDrawerToggle(true)
                .addDrawerItems(
                        armory_item,
                        community_item,
                        new DividerDrawerItem()
                )
                .withOnDrawerItemClickListener(new com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Fragment newFragment = getFragmentFromIdentifier(Integer.parseInt(String.valueOf(drawerItem.getIdentifier())));
                        ((MainActivity)activity).loadNewFragment(newFragment, false,false);
                        return false;
                    }
                })
                .build();
        drawer.setSelection(2);
    }


    public boolean isOpen(){
        return drawer.isDrawerOpen();

    }

    private Fragment getFragmentFromIdentifier(int id){
        switch (id){
            case 1 :
                return new FragmentWardRobe();
            case 2 :
                return new FragmentCommunity();
        }
        // ne passera jamais ici
        return new FragmentCommunity();

    }

    public void closeDrawer() {
        this.drawer.closeDrawer();
    }
}
