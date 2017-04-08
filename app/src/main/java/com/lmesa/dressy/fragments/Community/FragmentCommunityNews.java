package com.lmesa.dressy.fragments.Community;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lmesa.dressy.R;
import com.lmesa.dressy.activities.ActivityCommunityDetail;
import com.lmesa.dressy.adapters.AdapterCommunityList;
import com.lmesa.dressy.interfaces.CommunityListener;
import com.lmesa.dressy.models.Clothe;
import com.lmesa.dressy.models.Clothes;
import com.lmesa.dressy.models.Post;

import java.util.ArrayList;

/**
 * Created by Lucas on 08/04/2017.
 */

public class FragmentCommunityNews extends Fragment implements CommunityListener {
    private RecyclerView communityNewsList;
    private ArrayList<Post> listPosts;
    private Gson gson;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_community_list, container, false);
        listPosts = new ArrayList<Post>();
        communityNewsList = (RecyclerView) v.findViewById(R.id.community_list);
        gson = new Gson();
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for(Integer i = 0 ; i<= 50 ; i++){
            listPosts.add(new Post("Lucas"+i.toString(),"Ma super tenue","Ma super description",10, new Clothes("https://images.asos-media.com/products/asos-chemise-ultra-ajustee-a-carreaux-style-bucheron/7307603-1-burgundy?$XL$", new ArrayList<Clothe>())));
        }
        communityNewsList.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterCommunityList adapter = new AdapterCommunityList(getContext(), listPosts, R.layout.adapter_community_list);
        adapter.setListener(this);
        communityNewsList.setAdapter(adapter);
    }

    @Override
    public void onPostClicked(Post aPost) {
        Intent i = new Intent(getActivity(), ActivityCommunityDetail.class);
        i.putExtra("post", gson.toJson(aPost));
        startActivity(i);
    }
}
