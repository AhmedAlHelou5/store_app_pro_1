package com.ahmed.store_app_pro_1.ui.models;

import java.util.List;

public class ProductModel {


    private String category;
    private int image;
    private String title;
    private String description;
    private String numKm;
    private String numTime;

    private List<Integer> colors;
    private String price;
    private List<SliderImageHomeModel> sliderImageHomeModels;
    private  boolean isFavorite;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ProductModel(String category, int image, String title, String description, String numKm, String numTime, List<Integer> colors, String price, List<SliderImageHomeModel> sliderImageHomeModels, boolean isFavorite) {
        this.category = category;
        this.image = image;
        this.title = title;
        this.description = description;
        this.numKm = numKm;
        this.numTime = numTime;
        this.colors = colors;
        this.price = price;
        this.sliderImageHomeModels = sliderImageHomeModels;
        this.isFavorite = isFavorite;
    }

    public ProductModel(String title, String description, List<Integer> colors, String price, List<SliderImageHomeModel> sliderImageHomeModels, boolean isFavorite) {
        this.title = title;
        this.description = description;
        this.colors = colors;
        this.price = price;
        this.sliderImageHomeModels = sliderImageHomeModels;
        this.isFavorite = isFavorite;
    }

    public ProductModel(int image, String title, String numKm, String numTime) {
        this.image = image;
        this.title = title;
        this.numKm = numKm;
        this.numTime = numTime;
    }

    public ProductModel(int image, String title, String description, List<Integer> colors, String price, List<SliderImageHomeModel> sliderImageHomeModels, boolean isFavorite) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.colors = colors;
        this.price = price;
        this.sliderImageHomeModels = sliderImageHomeModels;
        this.isFavorite = isFavorite;
    }



    public ProductModel(int image, String title, String description, String numKm, String numTime, List<Integer> colors, String price, List<SliderImageHomeModel> sliderImageHomeModels, boolean isFavorite) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.numKm = numKm;
        this.numTime = numTime;
        this.colors = colors;
        this.price = price;
        this.sliderImageHomeModels = sliderImageHomeModels;
        this.isFavorite = isFavorite;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumKm() {
        return numKm;
    }

    public void setNumKm(String numKm) {
        this.numKm = numKm;
    }

    public String getNumTime() {
        return numTime;
    }

    public void setNumTime(String numTime) {
        this.numTime = numTime;
    }

    public List<Integer> getColors() {
        return colors;
    }

    public void setColors(List<Integer> colors) {
        this.colors = colors;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<SliderImageHomeModel> getSliderImageHomeModels() {
        return sliderImageHomeModels;
    }

    public void setSliderImageHomeModels(List<SliderImageHomeModel> sliderImageHomeModels) {
        this.sliderImageHomeModels = sliderImageHomeModels;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
