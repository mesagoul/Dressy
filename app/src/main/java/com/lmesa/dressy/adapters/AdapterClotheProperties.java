package com.lmesa.dressy.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lmesa.dressy.R;
import com.lmesa.dressy.activities.ActivityImageFullScreen;
import com.lmesa.dressy.interfaces.CommunityListener;
import com.lmesa.dressy.models.Clothe.Brand;
import com.lmesa.dressy.models.Clothe.Category;
import com.lmesa.dressy.models.Clothe.Clothe;
import com.lmesa.dressy.models.Clothe.Color;
import com.lmesa.dressy.models.Clothe.Material;
import com.lmesa.dressy.models.Post;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Lucas on 20/04/2017.
 */

public class AdapterClotheProperties extends BaseAdapter  {

    private Context context;
    private ArrayList<?> listProperties;

    public AdapterClotheProperties(Context context, ArrayList<?> listProperties){
        this.context = context;
        this.listProperties = listProperties;
    }


    @Override
    public int getCount() {
        if(listProperties != null){
            return listProperties.size();
        }else{
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return listProperties.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView,
                               ViewGroup parent) {
        TextView textview;

        Object property = listProperties.get(position);
        textview = new TextView(context);
        textview.setTextColor(ContextCompat.getColor(context, R.color.colorDark));
        textview.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150));
        textview.setGravity(Gravity.CENTER);

        if(property instanceof Brand){
            textview.setText(((Brand) property).getLibelle());
        }else if(property instanceof Material){
            textview.setText(((Material) property).getLibelle());
        }else if(property instanceof Category){
            textview.setText(((Category) property).getLibelle());
        }else if(property instanceof Color){
            textview.setText(((Color) property).getLibelle());
        }
        return textview;
    }
}