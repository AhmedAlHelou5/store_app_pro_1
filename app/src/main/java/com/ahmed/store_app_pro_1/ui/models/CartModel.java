package com.ahmed.store_app_pro_1.ui.models;

public class CartModel {

    private int image;
    private String title;
    private String price;
    private int quantity;
    private int discount;

    public CartModel(int image, String title, String price, int quantity, int discount) {
        this.image = image;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
    }

    public CartModel(int image, String title, String price, int quantity) {
        this.image = image;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
