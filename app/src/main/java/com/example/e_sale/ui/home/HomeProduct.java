package com.example.e_sale.ui.home;

public class HomeProduct {

    private String name;
    private String photoUrl;

    public HomeProduct() {
        // Default constructor required for calls to DataSnapshot.getValue(Product.class)
    }

    public HomeProduct(String name,String description, String photoUrl) {
        this.name = name;
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}

