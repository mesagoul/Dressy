package com.lmesa.dressy.helpers;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.lmesa.dressy.R;
import com.lmesa.dressy.interfaces.DialogListener;

/**
 * Created by Lucas on 16/04/2017.
 */

public class ManageDialog {
    private Activity activity;
    private DialogListener listener;
    private boolean share;

    public ManageDialog(Activity activity, boolean share){
        this.activity = activity;
        this.share = share;
    }

    public void loadDialog(){
        int cptTab;

        if(share){
            cptTab = 3;
        }else{
            cptTab = 2;
        }

        final Item[] items = new Item[cptTab];
        items[0] =  new Item(activity.getResources().getString(R.string.edit), R.drawable.ic_manage,ContextCompat.getColor(activity, R.color.colorAccent));
        items[1] =  new Item(activity.getResources().getString(R.string.delete), R.drawable.ic_delete,ContextCompat.getColor(activity, R.color.delete));
        if(share){
            items[2] =  new Item(activity.getResources().getString(R.string.share_on_socialwall), R.drawable.ic_share,ContextCompat.getColor(activity, R.color.primary));
        }




                ListAdapter adapter = new ArrayAdapter<Item>(
                activity.getApplicationContext(),
                android.R.layout.select_dialog_item,
                android.R.id.text1,
                items){
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                TextView tv = (TextView)v.findViewById(android.R.id.text1);
                tv.setTextColor(ContextCompat.getColor(activity, R.color.colorDark));
                tv.setBackgroundColor(items[position].getColor());

                //Put the image on the TextView
                tv.setCompoundDrawablesWithIntrinsicBounds(items[position].icon, 0, 0, 0);
                tv.setTextColor(ContextCompat.getColor(activity,R.color.colorLight));

                //Add margin between image and text (support various screen densities)
                int dp5 = (int) (5 * activity.getResources().getDisplayMetrics().density + 0.5f);
                tv.setCompoundDrawablePadding(dp5);


                return v;
            }
        };


        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setTitle(R.string.edit_title)
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            listener.onManageDialog();
                        }else if (which == 1){
                            listener.onDeleteDialog();
                        }else if(which == 2){
                            listener.onShareDialog();
                        }
                    }
                });

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void setListener(DialogListener listener) {
        this.listener = listener;
    }

    private static class Item{
        public final String text;
        public final int icon;
        private int color;
        private Item(String text, Integer icon, int color) {
            this.text = text;
            this.icon = icon;
            this.color = color;

        }
        @Override
        public String toString() {
            return text;
        }

        public int getColor() {
            return color;
        }
    }
}
