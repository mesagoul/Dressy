package com.lmesa.dressy.models;

import java.util.ArrayList;

/**
 * Created by Lucas on 08/04/2017.
 */

public class Clothes extends Object {

    private String urlImage;
    private ArrayList<Clothe> listClothe;

    public Clothes(String urlImage, ArrayList<Clothe> listClothe) {
        this.urlImage = urlImage;
        this.listClothe = listClothe;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public ArrayList<Clothe> getListClothe() {
        return listClothe;
    }

    public void setListClothe(ArrayList<Clothe> listClothe) {
        this.listClothe = listClothe;
    }
}
