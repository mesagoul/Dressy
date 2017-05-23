package com.lmesa.dressy.fragments.Community;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lmesa.dressy.R;
import com.lmesa.dressy.activities.ActivityCommunityDetail;
import com.lmesa.dressy.adapters.AdapterCommunityList;
import com.lmesa.dressy.helpers.ResponseHttp;
import com.lmesa.dressy.interfaces.CommunityListener;
import com.lmesa.dressy.interfaces.ServiceListener;
import com.lmesa.dressy.models.Clothe.Clothe;
import com.lmesa.dressy.models.Clothe.ClotheProperties;
import com.lmesa.dressy.models.Clothes;
import com.lmesa.dressy.models.Post;
import com.lmesa.dressy.network.ApiDressy;

import java.util.ArrayList;

/**
 * Created by Lucas on 08/04/2017.
 */

public class FragmentCommunityNews extends Fragment implements CommunityListener, ServiceListener {
    private RecyclerView communityNewsList;
    private ArrayList<Post> listPosts;
    private Gson gson;
    private ApiDressy apiDressy;
    private LinearLayout content;
    private ProgressBar progressBar;
    private TextView no_posts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_community_list, container, false);
        listPosts = new ArrayList<Post>();
        communityNewsList = (RecyclerView) v.findViewById(R.id.community_list);
        gson = new Gson();
        apiDressy = new ApiDressy(getActivity());
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        content = (LinearLayout) v.findViewById(R.id.view_content);
        no_posts = (TextView) v.findViewById(R.id.no_posts);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiDressy.setListener(this);

        progressBar.setVisibility(View.VISIBLE);
        content.setVisibility(View.GONE);

        communityNewsList.setLayoutManager(new LinearLayoutManager(getContext()));
        apiDressy.getLastPosts();

    }

    public void loadAdapter(){
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

    @Override
    public void onGetUser(boolean isSucces) {

    }

    @Override
    public void onCreateUser(boolean isSucces) {

    }

    @Override
    public void onGetClothe(boolean isSucces, ArrayList<Clothe> listClothe) {

    }

    @Override
    public void onCreateClothe(boolean isSucces, int cloth_id) {

    }

    @Override
    public void onDeleteClothe(boolean isSucces) {

    }

    @Override
    public void onManageClothes(boolean isSucces) {

    }

    @Override
    public void onGetClothes(boolean isSucces, ArrayList<Clothes> clothes) {

    }

    @Override
    public void onCreateClothes(boolean isSucces, int id) {

    }

    @Override
    public void onDeleteClothes(boolean isSucces) {

    }

    @Override
    public void onManageClothe(boolean isSucces) {

    }

    @Override
    public void onGetSimilarity(boolean isSucces) {

    }

    @Override
    public void onCreatePost(boolean isSucces) {

    }

    @Override
    public void onGetClotheProperties(boolean isSuccess, ClotheProperties clotheProperties) {

    }

    @Override
    public void onGetTopPosts(boolean isSuccess, ArrayList<Post> listPost) {

    }

    @Override
    public void onGetLastPosts(boolean isSuccess, ArrayList<Post> listPost) {
        progressBar.setVisibility(View.GONE);
        if(isSuccess){
            if (listPost == null){
                no_posts.setVisibility(View.VISIBLE);
            }else{
                this.listPosts = listPost;
                if(listPost.size() == 0){
                    no_posts.setVisibility(View.VISIBLE);
                }else {
                    no_posts.setVisibility(View.GONE);
                    content.setVisibility(View.VISIBLE);
                    this.loadAdapter();
                }
            }

        }else{
            new ResponseHttp(getContext()).onErrorGetPost();
        }
    }

    @Override
    public void onAddImage(boolean isSuccess, String urlImage) {

    }
}
