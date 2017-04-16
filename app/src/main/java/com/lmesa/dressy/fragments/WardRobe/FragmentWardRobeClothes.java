package com.lmesa.dressy.fragments.WardRobe;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.gson.Gson;
import com.lmesa.dressy.R;
import com.lmesa.dressy.activities.ActivityWardRobeClothesDetail;
import com.lmesa.dressy.adapters.AdapterWardRobeClothes;
import com.lmesa.dressy.interfaces.WardRobeListener;
import com.lmesa.dressy.models.Clothe;
import com.lmesa.dressy.models.Clothes;

import java.util.ArrayList;

/**
 * Created by Lucas on 09/04/2017.
 */

public class FragmentWardRobeClothes extends Fragment implements WardRobeListener {
    private GridView gridView;
    private ArrayList<Clothes> listClothes;
    private Gson gson;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_wardrobe_clothes,container,false);
        gridView = (GridView) v.findViewById(R.id.wardrobe_clothes_list);
        listClothes = new ArrayList<Clothes>();
        gson = new Gson();
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Again and again like a banana
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
        Clothes clothes1 =  new Clothes(
                "http://p7.storage.canalblog.com/75/10/1014555/83786375_o.jpg",
                new ArrayList<Clothe>(),
                0
        );
        Clothes clothes2 = new Clothes(
                "https://s-media-cache-ak0.pinimg.com/736x/3c/bc/4a/3cbc4a89555170d8564767ba31236745.jpg",
                new ArrayList<Clothe>(),
                0
        );
        Clothes clothes3 = new Clothes(
                "https://s-media-cache-ak0.pinimg.com/736x/cd/2f/6d/cd2f6d0eeca5a24c12fc8515b49a1746.jpg",
                new ArrayList<Clothe>(),
                0
        );
        Clothes clothes4 = new Clothes(
                "https://s-media-cache-ak0.pinimg.com/736x/80/b3/26/80b3267ccdf994b79717c9e31b5d5067.jpg",
                new ArrayList<Clothe>(),
                0
        );
        Clothes clothes5 = new Clothes(
                "https://tribune.menlook.com/wp-content/uploads/2013/04/marc-3-ok.jpg",
                new ArrayList<Clothe>(),
                0
        );


        for(int i = 0; i <= 15 ; i++){
            clothes1.getListClothe().add(clothe);
            clothes2.getListClothe().add(clothe);
            clothes3.getListClothe().add(clothe);
            clothes4.getListClothe().add(clothe);
            clothes5.getListClothe().add(clothe);
            clothes1.getListClothe().add(clothe2);
            clothes2.getListClothe().add(clothe2);
            clothes3.getListClothe().add(clothe2);
            clothes4.getListClothe().add(clothe2);
            clothes5.getListClothe().add(clothe2);
            listClothes.add(clothes1);
            listClothes.add(clothes2);
            listClothes.add(clothes3);
            listClothes.add(clothes4);
            listClothes.add(clothes5);
        }
        // end banana

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
        return false;

    }
}
