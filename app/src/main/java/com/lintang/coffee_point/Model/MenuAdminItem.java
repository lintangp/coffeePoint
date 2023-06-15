package com.lintang.coffee_point.Model;

public class MenuAdminItem {
    private int imageResource;
    private String namaMakanan;
    private String hargaMakanan;
    private String penjelasanMakanan;

    public MenuAdminItem(int imageResource, String namaMakanan, String hargaMakanan, String penjelasanMakanan) {
        this.imageResource = imageResource;
        this.namaMakanan = namaMakanan;
        this.hargaMakanan = hargaMakanan;
        this.penjelasanMakanan = penjelasanMakanan;
    }

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
