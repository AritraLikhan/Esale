package com.example.e_sale.ui.home;

public class HomeProduct implements java.io.Serializable {

    private String name;
    private String photoUrl;

    private String ownerID;
    private String description;

    public HomeProduct() {
        // Default constructor required for calls to DataSnapshot.getValue(Product.class)
    }

    public HomeProduct(String name,String description, String photoUrl) {
        this.name = name;
        this.photoUrl = photoUrl;
        this.description = description;
        this.ownerID = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }
}

