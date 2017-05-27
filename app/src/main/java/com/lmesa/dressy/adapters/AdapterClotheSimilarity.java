package com.lmesa.dressy.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lmesa.dressy.R;
import com.lmesa.dressy.interfaces.SimilarityListener;
import com.lmesa.dressy.models.Clothe.Clothe;

import java.util.ArrayList;

/**
 * Created by Lucas on 27/05/2017.
 */

public class AdapterClotheSimilarity extends RecyclerView.Adapter<AdapterClotheSimilarity.SimilarityViewHolder> {
    private Context context;
    private ArrayList<Clothe> listClothe;
    private boolean isPartner;
    private SimilarityListener listener;

    public AdapterClotheSimilarity(Context context, ArrayList<Clothe> listClothe,  boolean isPartner){
        this.listClothe = listClothe;
        this.context = context;
        this.isPartner = isPartner;
    }

    @Override
    public AdapterClotheSimilarity.SimilarityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_similarity , parent, false);
        AdapterClotheSimilarity.SimilarityViewHolder viewHolder = new AdapterClotheSimilarity.SimilarityViewHolder(rowView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(AdapterClotheSimilarity.SimilarityViewHolder holder, int position) {
        final Clothe aClothe = listClothe.get(position);

        Glide
                .with(context)
                .load(aClothe.getCloth_urlImage())
                .centerCrop()
                .crossFade()
                .into(holder.image);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(aClothe);
            }
        });


    }

    @Override
    public int getItemCount() {
        if(this.listClothe != null){
            return this.listClothe.size();
        }else{
            return 0;
        }
    }

    public void setListener(SimilarityListener listener) {
        this.listener = listener;
    }

    public class SimilarityViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;


        public SimilarityViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

}
