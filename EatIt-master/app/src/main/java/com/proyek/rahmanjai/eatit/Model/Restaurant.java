package com.proyek.rahmanjai.eatit.Model;

public class Restaurant {
    private String name,image;

    public Restaurant(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Restaurant() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
