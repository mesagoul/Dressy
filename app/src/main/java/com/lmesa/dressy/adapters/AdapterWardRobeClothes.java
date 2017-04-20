package com.lmesa.dressy.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lmesa.dressy.R;
import com.lmesa.dressy.interfaces.WardRobeListener;
import com.lmesa.dressy.models.Clothe.Clothe;
import com.lmesa.dressy.models.Clothes;

import java.util.ArrayList;

/**
 * Created by Lucas on 09/04/2017.
 */

public class AdapterWardRobeClothes extends BaseAdapter {
    private Activity activity;
    private Context context;
    private ArrayList<?> listClothes;
    private WardRobeListener listener;

    public AdapterWardRobeClothes(Activity activity, ArrayList<?> listClothes) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.listClothes = listClothes;
    }

    @Override
    public int getCount() {
        if(this.listClothes != null){
            return this.listClothes.size();
        }else{
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return listClothes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setBackgroundResource(R.drawable.image_view_border);
        } else {
            imageView = (ImageView) convertView;
        }
        if(listClothes.get(position) instanceof Clothes) {
            Clothes aClothes = (Clothes) listClothes.get(position);
            Glide
                    .with(context)
                    .load(aClothes.getUrlImage())
                    .centerCrop()
                    .crossFade()
                    .into(imageView);
        }else{
            Clothe aClothe = (Clothe) listClothes.get(position);
            Glide
                    .with(context)
                    .load(aClothe.getCloth_urlImage())
                    .centerCrop()
                    .crossFade()
                    .into(imageView);
        }


        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
               return listener.onLongClick(position);
            }
        });



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.loadDetail(position);
            }
        });






        return imageView;
    }


    public void setListener(WardRobeListener listener) {
        this.listener = listener;
    }
}
