package com.lmesa.dressy.models.Clothe;

/**
 * Created by Lucas on 20/04/2017.
 */

public class Category {
    private  int id;
    private String libelle;
    private String id_fann;

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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Category){
            if(((Category) obj).getId() == this.getId()){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
}
