package com.lmesa.dressy.fragments.WardRobe;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lmesa.dressy.R;
import com.lmesa.dressy.activities.ActivityCommunityDetail;
import com.lmesa.dressy.activities.ActivityCreateClothe;
import com.lmesa.dressy.activities.ActivityWardRobeClotheDetail;
import com.lmesa.dressy.adapters.AdapterWardRobeClothes;
import com.lmesa.dressy.helpers.ManageDialog;
import com.lmesa.dressy.interfaces.DialogListener;
import com.lmesa.dressy.interfaces.ServiceListener;
import com.lmesa.dressy.interfaces.WardRobeListener;
import com.lmesa.dressy.models.Clothe;
import com.lmesa.dressy.models.Clothes;
import com.lmesa.dressy.network.ApiDressy;

import java.util.ArrayList;

/**
 * Created by Lucas on 09/04/2017.
 */

public class FragmentWardRobeClothe extends Fragment implements WardRobeListener, DialogListener, ServiceListener{
    private GridView gridView;
    private ArrayList<Clothe> listClothe;
    private Button btn_add;
    private Gson gson;
    private ApiDressy apiDressy;
    private Clothe currentClothe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_wardrobe_clothe,container,false);
        gridView = (GridView) v.findViewById(R.id.wardrobe_clothes_list);
        listClothe = new ArrayList<Clothe>();
        btn_add = (Button) v.findViewById(R.id.btn_add);
        apiDressy = new ApiDressy(getActivity());
        apiDressy.setListener(this);
        gson = new Gson();
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toAddClothe = new Intent(getContext(), ActivityCreateClothe.class);
                startActivity(toAddClothe);
            }
        });

        // banana
        Clothe clothe = new Clothe(
                "Name",
                "vert",
                "noReference",
                "http://blzjeans.com/8831-29091-thickbox/tee-shirt-vert-homme-tendance-et-fashion-lenny-and-loyd.jpg",
                "category",
                "brand",
                "material"
        );

        Clothe clothe2 = new Clothe(
                "Name",
                "Jaune",
                "noReference",
                "http://i2.cdscdn.com/pdt2/1/4/1/1/300x300/mp02972141/rw/pantalon-elastique-zip-couleur-pure-femme-jaune.jpg",
                "category",
                "brand",
                "material"
        );
        Clothe clothe3 = new Clothe(
                "Name",
                "Jaune",
                "noReference",
                "http://media.meltystyle.fr/article-1645763-ajust_930-f1374762253/asos-bermuda-chino-29-16-eur.jpg",
                "category",
                "brand",
                "material"
        );


        for(int i = 0; i <= 15 ; i++){
            listClothe.add(clothe);
            listClothe.add(clothe2);
            listClothe.add(clothe3);
            listClothe.add(clothe2);
            listClothe.add(clothe);
        }
        // banana
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
        ManageDialog dialog = new ManageDialog(getActivity());
        dialog.setListener(this);
        dialog.loadDialog();
        return true;
    }

    @Override
    public void onManageDialog() {
        Intent toManageActivity = new Intent(getActivity(), ActivityCreateClothe.class);
        toManageActivity.putExtra("manage","true");
        toManageActivity.putExtra("editClothe",gson.toJson(currentClothe,Clothe.class));
        getActivity().startActivity(toManageActivity);
    }

    @Override
    public void onDeleteDialog() {
        apiDressy.deleteClothe(this.currentClothe);
    }

    @Override
    public void onGetUser() {

    }

    @Override
    public void onCreateUser() {

    }

    @Override
    public void onGetClothe() {

    }

    @Override
    public void onCreateClothe() {

    }

    @Override
    public void onDeleteClothe() {
        Toast.makeText(getContext(),"Test Delete Clothe",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onManageClothes() {

    }

    @Override
    public void onGetClothes() {

    }

    @Override
    public void onCreateClothes() {

    }

    @Override
    public void onDeleteClothes() {

    }

    @Override
    public void onManageClothe() {

    }

    @Override
    public void onGetSimilarity() {

    }
}
