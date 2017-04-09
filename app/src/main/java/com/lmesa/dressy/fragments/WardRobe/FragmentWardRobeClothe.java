package com.lmesa.dressy.fragments.WardRobe;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.lmesa.dressy.R;
import com.lmesa.dressy.adapters.AdapterWardRobeClothes;
import com.lmesa.dressy.models.Clothe;
import com.lmesa.dressy.models.Clothes;

import java.util.ArrayList;

/**
 * Created by Lucas on 09/04/2017.
 */

public class FragmentWardRobeClothe extends Fragment {
    private GridView gridView;
    private ArrayList<Clothe> listClothe;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_wardrobe_clothe,container,false);
        gridView = (GridView) v.findViewById(R.id.wardrobe_clothes_list);
        listClothe = new ArrayList<Clothe>();
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // banana

        Clothe clothe = new Clothe(
                "Name",
                "blue",
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


        for(int i = 0; i <= 15 ; i++){
            listClothe.add(clothe);
            listClothe.add(clothe2);
        }
        // banana
        AdapterWardRobeClothes adapterWardRobeClothes = new AdapterWardRobeClothes(getContext(),listClothe);
        // TODO ADD LISTENER
        //adapterWardRobeClothes.setListener(this);
        gridView.setAdapter(adapterWardRobeClothes);
    }
}
