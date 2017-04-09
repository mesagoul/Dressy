package com.lmesa.dressy.models;

/**
 * Created by Lucas on 08/04/2017.
 */

public class Clothe {
    private String cloth_name;
    private String cloth_color;
    private String cloth_reference;
    private String cloth_urlImage;
    private String cloth_category;
    private String cloth_brand;
    private String cloth_material;
    private String cloth_partner;

    public Clothe(String cloth_name, String cloth_color, String cloth_reference, String cloth_urlImage, String cloth_category, String cloth_brand, String cloth_material) {
        this.cloth_name = cloth_name;
        this.cloth_color = cloth_color;
        this.cloth_reference = cloth_reference;
        this.cloth_urlImage = cloth_urlImage;
        this.cloth_category = cloth_category;
        this.cloth_brand = cloth_brand;
        this.cloth_material = cloth_material;
    }

    public String getCloth_urlImage() {
        return cloth_urlImage;
    }

    public void setCloth_urlImage(String cloth_urlImage) {
        this.cloth_urlImage = cloth_urlImage;
    }

    public String getCloth_name() {
        return cloth_name;
    }

    public void setCloth_name(String cloth_name) {
        this.cloth_name = cloth_name;
    }

    public String getCloth_color() {
        return cloth_color;
    }

    public void setCloth_color(String cloth_color) {
        this.cloth_color = cloth_color;
    }

    public String getCloth_reference() {
        return cloth_reference;
    }

    public void setCloth_reference(String cloth_reference) {
        this.cloth_reference = cloth_reference;
    }

    public String getCloth_category() {
        return cloth_category;
    }

    public void setCloth_category(String cloth_category) {
        this.cloth_category = cloth_category;
    }

    public String getCloth_brand() {
        return cloth_brand;
    }

    public void setCloth_brand(String cloth_brand) {
        this.cloth_brand = cloth_brand;
    }

    public String getCloth_material() {
        return cloth_material;
    }

    public void setCloth_material(String cloth_material) {
        this.cloth_material = cloth_material;
    }

    public String getCloth_partner() {
        return cloth_partner;
    }

    public void setCloth_partner(String cloth_partner) {
        this.cloth_partner = cloth_partner;
    }
}
