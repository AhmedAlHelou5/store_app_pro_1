package com.ahmed.store_app_pro_1.ui.models;

public class OfferModel {
    private int image;
    private String name;
    private String description;
    private String oldPrice;
    private String newPrice;

    public OfferModel(int image, String name, String description, String oldPrice, String newPrice) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }
}
