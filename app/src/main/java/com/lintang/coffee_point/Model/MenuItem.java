package com.lintang.coffee_point.Model;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class MenuItem implements Serializable {
    @Exclude
    private String id;
    private int imageResource;
    private String namaMakanan;
    private String hargaMakanan;
    private String penjelasanMakanan;

    public MenuItem(int imageResource, String namaMakanan, String hargaMakanan, String penjelasanMakanan) {
        this.imageResource = imageResource;
        this.namaMakanan = namaMakanan;
        this.hargaMakanan = hargaMakanan;
        this.penjelasanMakanan = penjelasanMakanan;
    }

    public String getId() {return this.id;}

    public void setId(String id) {this.id = id;}

    public int getImageResource() {
        return imageResource;
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public String getHargaMakanan() {
        return hargaMakanan;
    }

    public String getPenjelasanMakanan() {
        return penjelasanMakanan;
    }
}
