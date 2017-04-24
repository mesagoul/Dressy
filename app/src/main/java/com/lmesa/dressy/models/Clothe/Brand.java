package com.lmesa.dressy.models.Clothe;

/**
 * Created by Lucas on 20/04/2017.
 */

public class Brand {
    private int id;
    private String libelle;

    public Brand(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Brand){
            if(((Brand) obj).getId() == this.getId()){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
}
