package com.proyek.rahmanjai.eatit.Model;

import java.util.List;



public class Request {
    private String phone;
    private String name;
    private String address;
    private String total;
    private String status;
    private String comment;
    private String restaurantId;
    private String selfPickup ;
    private List<Order> foods; // list of food order


    public Request() {

    }


    public Request(String selfPickup) {
        this.selfPickup = selfPickup;
    }

    public Request(String phone, String name, String address, String total, String status, String comment, String restaurantId, List<Order> foods) {

        this.phone = phone;
        this.name = name;
        this.address = address;
        this.total = total;
        this.status = status;
        this.comment = comment;
        this.restaurantId = restaurantId;
        this.foods = foods;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getSelfPickup() {
        return selfPickup;
    }

    public void setSelfPickup(String selfPickup) {
        this.selfPickup = selfPickup;
    }

    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }
}
