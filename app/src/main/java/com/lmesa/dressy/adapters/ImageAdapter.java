package com.lmesa.dressy.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lmesa.dressy.activities.ActivityImageFullScreen;
import com.lmesa.dressy.models.Clothe;
import com.lmesa.dressy.models.Post;

import java.util.ArrayList;

/**
 * Created by Lucas on 09/04/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Clothe> listClothe;

    public ImageAdapter(Context c, ArrayList<Clothe> listClothe){
        this.context = c;
        this.listClothe = listClothe;

    }

    @Override
    public int getCount() {
        return listClothe.size();
    }

    @Override
    public Object getItem(int position) {
        return listClothe.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        final Clothe aClothe = listClothe.get(position);
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(3, 3, 3, 3);
        } else {
            imageView = (ImageView) convertView;
        }

        Glide
                .with(context)
                .load(aClothe.getCloth_urlImage())
                .centerCrop()
                .crossFade()
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ActivityImageFullScreen.class);
                i.putExtra("image",aClothe.getCloth_urlImage());
                context.startActivity(i);
            }
        });

        return imageView;
    }
}
