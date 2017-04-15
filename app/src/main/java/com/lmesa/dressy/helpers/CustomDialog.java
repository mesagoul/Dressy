package com.lmesa.dressy.helpers;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.lmesa.dressy.R;
import com.lmesa.dressy.activities.ActivityWardRobeClotheList;

/**
 * Created by Lucas on 15/04/2017.
 */

public class CustomDialog{
    private Activity activity;
    public CustomDialog(Activity activity){
        this.activity = activity;
    }

    public void loadDialog(){

        final Item[] items = {
                new Item("Mon armoire", R.drawable.ic_armory),
                new Item("Mon appareil photo", R.drawable.ic_photo),
        };

        ListAdapter adapter = new ArrayAdapter<Item>(
                activity.getApplicationContext(),
                android.R.layout.select_dialog_item,
                android.R.id.text1,
                items){
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                TextView tv = (TextView)v.findViewById(android.R.id.text1);
                tv.setTextColor(ContextCompat.getColor(activity, R.color.colorDark));

                //Put the image on the TextView
                tv.setCompoundDrawablesWithIntrinsicBounds(items[position].icon, 0, 0, 0);

                //Add margin between image and text (support various screen densities)
                int dp5 = (int) (5 * activity.getResources().getDisplayMetrics().density + 0.5f);
                tv.setCompoundDrawablePadding(dp5);

                return v;
            }
        };


        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setTitle(R.string.match_title)
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            Intent toClotheListActivity = new Intent(activity, ActivityWardRobeClotheList.class);
                            activity.startActivity(toClotheListActivity);
                        }else if (which == 1){
                            Log.d("DEBUG", "SELECT PHONE");
                        }
                    }
                });

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static class Item{
        public final String text;
        public final int icon;
        public Item(String text, Integer icon) {
            this.text = text;
            this.icon = icon;
        }
        @Override
        public String toString() {
            return text;
        }
    }
}
