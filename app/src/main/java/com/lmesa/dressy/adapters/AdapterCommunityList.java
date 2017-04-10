package com.lmesa.dressy.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lmesa.dressy.R;
import com.lmesa.dressy.interfaces.CommunityListener;
import com.lmesa.dressy.models.Post;

import java.util.ArrayList;

/**
 * Created by Lucas on 08/04/2017.
 */

public class AdapterCommunityList extends RecyclerView.Adapter<AdapterCommunityList.CommunityViewHolder> {
    private  Context context;
    private  ArrayList<Post> listPosts;
    private  int layout;
    private CommunityListener listener;
    //private OnRapportListener listener;

    public AdapterCommunityList(Context context, ArrayList<Post> listPosts, int layout){
        this.listPosts = listPosts;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public AdapterCommunityList.CommunityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(this.layout , parent, false);
        AdapterCommunityList.CommunityViewHolder viewHolder = new AdapterCommunityList.CommunityViewHolder(rowView);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(AdapterCommunityList.CommunityViewHolder holder, int position) {
        final Post aPost = listPosts.get(position);
        holder.username.setText(aPost.getUsername());
        holder.points.setText(aPost.getClothes().getScore().toString());
        holder.title.setText(aPost.getTitle());
        holder.desc.setText(aPost.getDesc());

        Glide
                .with(context)
                .load(aPost.getClothes().getUrlImage())
                .centerCrop()
                .crossFade()
                .into(holder.image);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPostClicked(aPost);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listPosts.size();
    }

    public void setListener(CommunityListener listener) {
        this.listener = listener;
    }

    public class CommunityViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView username;
        private TextView points;
        private TextView title;
        private TextView desc;


        public CommunityViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.adapter_community_image);
            username = (TextView) itemView.findViewById(R.id.adapter_community_username);
            points = (TextView) itemView.findViewById(R.id.adapter_community_points);
            title = (TextView) itemView.findViewById(R.id.adapter_community_title);
            desc = (TextView) itemView.findViewById(R.id.adapter_community_desc);
        }
    }
}
