package com.lmesa.dressy.models;

import com.lmesa.dressy.models.Clothe.Clothe;

import java.util.ArrayList;

/**
 * Created by Lucas on 08/04/2017.
 */

public class Clothes{

    private int id;
    private String urlImage;
    private ArrayList<Clothe> listClothe;
    private Integer score;

    public Clothes(int id, String urlImage, ArrayList<Clothe> listClothe, Integer score) {
        this.id = id;
        this.urlImage = urlImage;
        this.listClothe = listClothe;
        this.score = score;
    }
    public Clothes(String urlImage, ArrayList<Clothe> listClothe) {
        this.urlImage = urlImage;
        this.listClothe = listClothe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }
}
