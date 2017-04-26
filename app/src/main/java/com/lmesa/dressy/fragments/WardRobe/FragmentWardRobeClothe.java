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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lmesa.dressy.R;
import com.lmesa.dressy.activities.ActivityManageClothe;
import com.lmesa.dressy.activities.ActivityWardRobeClotheDetail;
import com.lmesa.dressy.adapters.AdapterWardRobeClothes;
import com.lmesa.dressy.helpers.ManageDialog;
import com.lmesa.dressy.helpers.ResponseHttp;
import com.lmesa.dressy.interfaces.DialogListener;
import com.lmesa.dressy.interfaces.ServiceListener;
import com.lmesa.dressy.interfaces.WardRobeListener;
import com.lmesa.dressy.models.Clothe.Clothe;
import com.lmesa.dressy.models.Clothe.ClotheProperties;
import com.lmesa.dressy.models.Clothes;
import com.lmesa.dressy.network.ApiDressy;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Lucas on 09/04/2017.
 */

public class FragmentWardRobeClothe extends Fragment implements WardRobeListener, DialogListener, ServiceListener{

    private static final int CODE_CREATE_CLOTHE = 298;
    private static final int CODE_MANAGE_CLOTHE = 472;
    private GridView gridView;
    private ArrayList<Clothe> listClothe;
    private Button btn_add;
    private Gson gson;
    private ApiDressy apiDressy;
    private Clothe currentClothe;
    private LinearLayout content;
    private ProgressBar progressBar;
    private TextView no_clothe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_wardrobe_clothe,container,false);
        gridView = (GridView) v.findViewById(R.id.wardrobe_clothes_list);
        listClothe = new ArrayList<Clothe>();
        btn_add = (Button) v.findViewById(R.id.btn_add);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        content = (LinearLayout) v.findViewById(R.id.view_content);
        no_clothe = (TextView) v.findViewById(R.id.no_clothe);
        apiDressy = new ApiDressy(getActivity());
        apiDressy.setListener(this);
        gson = new Gson();
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar.setVisibility(View.VISIBLE);
        content.setVisibility(View.GONE);
        btn_add.setVisibility(View.GONE);
        apiDressy.getClothe();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toAddClothe = new Intent(getContext(), ActivityManageClothe.class);
                toAddClothe.putExtra("create","true");
                startActivityForResult(toAddClothe,CODE_CREATE_CLOTHE);
            }
        });
    }

    public void loadAdapter(){
        AdapterWardRobeClothes adapterWardRobeClothes = new AdapterWardRobeClothes(getActivity(),listClothe);
        adapterWardRobeClothes.setListener(this);
        gridView.setAdapter(adapterWardRobeClothes);
    }

    @Override
    public void loadDetail(int position) {
        Clothe aClothe = listClothe.get(position);
        Intent i = new Intent(getActivity(), ActivityWardRobeClotheDetail.class);
        i.putExtra("clothe", gson.toJson(aClothe));
        startActivity(i);
    }

    @Override
    public boolean onLongClick(int position) {
        this.currentClothe = listClothe.get(position);
        ManageDialog dialog = new ManageDialog(getActivity(), false);
        dialog.setListener(this);
        dialog.loadDialog();
        return true;
    }

    @Override
    public void onManageDialog() {
        Intent toManageActivity = new Intent(getActivity(), ActivityManageClothe.class);
        toManageActivity.putExtra("manage","true");
        toManageActivity.putExtra("editClothe",gson.toJson(currentClothe,Clothe.class));
        startActivityForResult(toManageActivity,CODE_MANAGE_CLOTHE);
    }

    @Override
    public void onDeleteDialog() {
        progressBar.setVisibility(View.VISIBLE);
        content.setVisibility(View.GONE);
        btn_add.setVisibility(View.VISIBLE);
        apiDressy.deleteClothe(this.currentClothe);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODE_CREATE_CLOTHE && resultCode == RESULT_OK){
            Clothe clothe = gson.fromJson(data.getStringExtra("clothe"), Clothe.class);
            listClothe.add(clothe);
            loadAdapter();
        }else if (requestCode == CODE_MANAGE_CLOTHE && resultCode == RESULT_OK){
            Clothe clothe = gson.fromJson(data.getStringExtra("clothe"), Clothe.class);
            listClothe.remove(currentClothe);
            listClothe.add(clothe);
            loadAdapter();
        }
    }

    @Override
    public void onShareDialog() {

    }

    @Override
    public void onGetUser(boolean isSuccess) {

    }

    @Override
    public void onCreateUser(boolean isSuccess) {

    }

    @Override
    public void onGetClothe(boolean isSuccess, ArrayList<Clothe> listClothe) {
        progressBar.setVisibility(View.GONE);
        btn_add.setVisibility(View.VISIBLE);
        if(isSuccess){
            if(listClothe == null){
                no_clothe.setVisibility(View.VISIBLE);
            }else{
                this.listClothe = listClothe;
                if(listClothe.size() == 0){
                    no_clothe.setVisibility(View.VISIBLE);
                }else{
                    no_clothe.setVisibility(View.GONE);
                    content.setVisibility(View.VISIBLE);
                    this.loadAdapter();
                }
            }
        }else{
            new ResponseHttp(getContext()).onErrorGetClothe();
        }

    }

    @Override
    public void onCreateClothe(boolean isSuccess, int cloth_id) {

    }

    @Override
    public void onDeleteClothe(boolean isSuccess) {
        progressBar.setVisibility(View.GONE);
        btn_add.setVisibility(View.VISIBLE);
        if(isSuccess){
            listClothe.remove(this.currentClothe);
            if(listClothe == null){
                no_clothe.setVisibility(View.VISIBLE);
            }else{
                if(listClothe.size() == 0){
                    no_clothe.setVisibility(View.VISIBLE);
                }else {
                    no_clothe.setVisibility(View.GONE);
                    content.setVisibility(View.VISIBLE);
                    loadAdapter();
                }
            }
        }else{
            new ResponseHttp(getContext()).onErrorDeleteClothe();

        }
    }

    @Override
    public void onManageClothes(boolean isSuccess) {

    }

    @Override
    public void onGetClothes(boolean isSuccess, ArrayList<Clothes> clothes) {

    }

    @Override
    public void onCreateClothes(boolean isSuccess, int id) {

    }

    @Override
    public void onDeleteClothes(boolean isSuccess) {

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

    @Override
    public void onGetClotheProperties(boolean isSuccess, ClotheProperties clotheProperties) {

    }
}
