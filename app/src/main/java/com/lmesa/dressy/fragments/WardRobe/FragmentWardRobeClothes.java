package com.lmesa.dressy.fragments.WardRobe;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lmesa.dressy.R;
import com.lmesa.dressy.activities.ActivityManageClothes;
import com.lmesa.dressy.activities.ActivityManagePost;
import com.lmesa.dressy.activities.ActivityWardRobeClothesDetail;
import com.lmesa.dressy.adapters.AdapterWardRobeClothes;
import com.lmesa.dressy.helpers.ManageDialog;
import com.lmesa.dressy.helpers.ResponseHttp;
import com.lmesa.dressy.interfaces.DialogListener;
import com.lmesa.dressy.interfaces.ServiceListener;
import com.lmesa.dressy.interfaces.WardRobeListener;
import com.lmesa.dressy.models.Clothe.Clothe;
import com.lmesa.dressy.models.Clothes;
import com.lmesa.dressy.network.ApiDressy;

import java.util.ArrayList;

/**
 * Created by Lucas on 09/04/2017.
 */

public class FragmentWardRobeClothes extends Fragment implements WardRobeListener, DialogListener, ServiceListener{
    private GridView gridView;
    private ArrayList<Clothes> listClothes;
    private Gson gson;
    private ApiDressy apiDressy;
    private Clothes currentClothes;
    private Button btn_add;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_wardrobe_clothes,container,false);
        gridView = (GridView) v.findViewById(R.id.wardrobe_clothes_list);
        listClothes = new ArrayList<Clothes>();
        gson = new Gson();
        apiDressy = new ApiDressy(getActivity());
        btn_add = (Button) v.findViewById(R.id.btn_add);
        apiDressy.setListener(this);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiDressy.getClothes();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toCreateClothe = new Intent(getActivity(),ActivityManageClothes.class);
                toCreateClothe.putExtra("create","true");
                startActivity(toCreateClothe);
            }
        });
    }

    public void loadAdapter(){
        AdapterWardRobeClothes adapterWardRobeClothes = new AdapterWardRobeClothes(getActivity(),listClothes);
        adapterWardRobeClothes.setListener(this);
        gridView.setAdapter(adapterWardRobeClothes);
    }

    @Override
    public void loadDetail(int position) {
        Clothes clothes = listClothes.get(position);
        Intent i = new Intent(getActivity(), ActivityWardRobeClothesDetail.class);
        i.putExtra("clothes", gson.toJson(clothes));
        startActivity(i);
    }

    @Override
    public boolean onLongClick(int position) {
        this.currentClothes = listClothes.get(position);
        ManageDialog manageDialog = new ManageDialog(getActivity(),true);
        manageDialog.setListener(this);
        manageDialog.loadDialog();
        return true;

    }

    @Override
    public void onManageDialog() {
        // TODO
        Intent toManageClothes = new Intent(getActivity(), ActivityManageClothes.class);
        toManageClothes.putExtra("manage","true");
        toManageClothes.putExtra("clothes",gson.toJson(this.currentClothes,Clothes.class));
        startActivity(toManageClothes);
    }

    @Override
    public void onDeleteDialog() {
        apiDressy.deleteClothes(this.currentClothes);
    }

    @Override
    public void onShareDialog() {
        Intent toManagePost = new Intent(getActivity(), ActivityManagePost.class);
        toManagePost.putExtra("share","true");
        toManagePost.putExtra("clothes",gson.toJson(this.currentClothes,Clothes.class));
        startActivity(toManagePost);
    }

    @Override
    public void onGetUser(boolean isSuccess) {

    }

    @Override
    public void onCreateUser(boolean isSuccess) {

    }

    @Override
    public void onGetClothe(boolean isSuccess, ArrayList<Clothe> listClothe) {

    }

    @Override
    public void onCreateClothe(boolean isSuccess) {

    }

    @Override
    public void onDeleteClothe(boolean isSuccess) {

    }

    @Override
    public void onManageClothes(boolean isSuccess) {

    }

    @Override
    public void onGetClothes(boolean isSuccess, ArrayList<Clothes> clothes) {
        if(isSuccess){
            this.listClothes = clothes;
            this.loadAdapter();
        }else{
            new ResponseHttp(getContext()).onErrorGetClothes();
        }

    }

    @Override
    public void onCreateClothes(boolean isSuccess) {

    }

    @Override
    public void onDeleteClothes(boolean isSuccess) {
        if (isSuccess){
            Toast.makeText(getContext(),"Test delete Clothes",Toast.LENGTH_SHORT).show();
        }else{
            new ResponseHttp(getContext()).onErrorDeleteClothes();

        }
    }

    @Override
    public void onManageClothe(boolean isSuccess) {

    }

    @Override
    public void onGetSimilarity(boolean isSuccess) {

    }

    @Override
    public void onCreatePost(boolean isSuccess) {

    }
}
