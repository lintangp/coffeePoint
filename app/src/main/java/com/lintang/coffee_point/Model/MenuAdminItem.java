package com.lintang.coffee_point.Model;

public class MenuAdminItem {
    private String id;
    private String imageResource;
    private String namaMakanan;
    private String hargaMakanan;
    private String penjelasanMakanan;

    public MenuAdminItem(String id, String namaMakanan, String hargaMakanan, String penjelasanMakanan, String gambar) {
        this.id = id;
        this.imageResource = gambar;
        this.namaMakanan = namaMakanan;
        this.hargaMakanan = hargaMakanan;
        this.penjelasanMakanan = penjelasanMakanan;
    }

    public String getId() { return id; }

    public String getImageResource() {
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
