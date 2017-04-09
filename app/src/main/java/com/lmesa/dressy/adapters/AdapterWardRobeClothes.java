package com.lmesa.dressy.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lmesa.dressy.R;
import com.lmesa.dressy.fragments.WardRobe.FragmentWardRobeClothe;
import com.lmesa.dressy.models.Clothe;
import com.lmesa.dressy.models.Clothes;

import java.util.ArrayList;

/**
 * Created by Lucas on 09/04/2017.
 */

public class AdapterWardRobeClothes extends BaseAdapter {
    private Context context;
    private ArrayList<?> listClothes;

    public AdapterWardRobeClothes(Context context, ArrayList<?> listClothes) {
        this.context = context;
        this.listClothes = listClothes;
    }

    @Override
    public int getCount() {
        return this.listClothes.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
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



       /* imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ActivityImageFullScreen.class);
                i.putExtra("image",aClothe.getCloth_urlImage());
                context.startActivity(i);
            }
        });*/




        return imageView;
    }


}
