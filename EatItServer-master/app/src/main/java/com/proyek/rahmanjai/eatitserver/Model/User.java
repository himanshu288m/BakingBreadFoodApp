package com.proyek.rahmanjai.eatitserver.Model;





public class User {
    private String Nama, Password, IsStaff, Phone,restaurantId,Image,Name;

    public User(String nama, String password, String RestaurantId ) {
        Nama = nama;
        Password = password;
        IsStaff = "true";
        restaurantId = RestaurantId;

    }

    public User(String nama,String phone,String name,String image){
        Nama = nama;
        Phone = phone ;
        Name = name;
        Image = image;
    }

    public User(String image){
        Image = image;
    }

    public User() {

    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getIsStaff() {
        return IsStaff;
    }

    public void setIsStaff(String isStaff) {
        IsStaff = isStaff;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
