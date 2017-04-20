package com.lmesa.dressy.models.Clothe;

/**
 * Created by Lucas on 20/04/2017.
 */

public class Category {
    private  int id;
    private String libelle;

    public Category(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
