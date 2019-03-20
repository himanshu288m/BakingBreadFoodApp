package com.proyek.rahmanjai.eatit.Model;



public class Category {
    private String Nama;
    private String Image;

    public Category() {

    }

    public Category(String nama, String image) {
        Nama = nama;
        Image = image;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
