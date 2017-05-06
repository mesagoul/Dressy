package com.lmesa.dressy.models.Clothe;

/**
 * Created by Lucas on 06/05/2017.
 */

public class Color {
    private int id;
    private String libelle;
    private String id_fann;

    public Color(int id, String libelle) {
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
        if(obj instanceof Color){
            if(((Color) obj).getId() == this.getId()){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
}
