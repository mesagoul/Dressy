package com.lmesa.dressy.models.Clothe;

/**
 * Created by Lucas on 08/04/2017.
 */

public class Clothe{
    private int cloth_id;
    private String cloth_name;
    private String cloth_color;
    private String cloth_reference;
    private String cloth_urlImage;
    private String cloth_partner;
    private String cloth_bitmap;

    private Brand cloth_brand;
    private Material cloth_material;
    private Category cloth_category;


    public Clothe(String cloth_name, String cloth_color, String cloth_reference, String cloth_urlImage, Brand cloth_brand, Material cloth_material, Category cloth_category) {
        this.cloth_name = cloth_name;
        this.cloth_color = cloth_color;
        this.cloth_reference = cloth_reference;
        this.cloth_urlImage = cloth_urlImage;
        this.cloth_brand = cloth_brand;
        this.cloth_material = cloth_material;
        this.cloth_category = cloth_category;
    }

    public int getCloth_id() {
        return cloth_id;
    }

    public void setCloth_id(int cloth_id) {
        this.cloth_id = cloth_id;
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

    public String getCloth_urlImage() {
        return cloth_urlImage;
    }

    public void setCloth_urlImage(String cloth_urlImage) {
        this.cloth_urlImage = cloth_urlImage;
    }

    public String getCloth_partner() {
        return cloth_partner;
    }

    public void setCloth_partner(String cloth_partner) {
        this.cloth_partner = cloth_partner;
    }

    public String getCloth_bitmap() {
        return cloth_bitmap;
    }

    public void setCloth_bitmap(String cloth_bitmap) {
        this.cloth_bitmap = cloth_bitmap;
    }

    public Brand getCloth_brand() {
        return cloth_brand;
    }

    public void setCloth_brand(Brand cloth_brand) {
        this.cloth_brand = cloth_brand;
    }

    public Material getCloth_material() {
        return cloth_material;
    }

    public void setCloth_material(Material cloth_material) {
        this.cloth_material = cloth_material;
    }

    public Category getCloth_category() {
        return cloth_category;
    }

    public void setCloth_category(Category cloth_category) {
        this.cloth_category = cloth_category;
    }
}