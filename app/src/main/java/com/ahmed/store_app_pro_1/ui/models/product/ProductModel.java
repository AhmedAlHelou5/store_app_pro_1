package com.ahmed.store_app_pro_1.ui.models.product;

import com.ahmed.store_app_pro_1.ui.models.category.CategoryModel;
import com.ahmed.store_app_pro_1.ui.models.colors.ColorModel;
import com.ahmed.store_app_pro_1.ui.models.images.ImageModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductModel implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("category_id")
    @Expose
    private int categoryId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("offer_price")
    @Expose
    private int offerPrice;
    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("is_offer")
    @Expose
    private boolean isOffer;
    @SerializedName("isFavorite")
    @Expose
    private boolean isFavorite;
    @SerializedName("images")
    @Expose
    private List<ImageModel> images;
    @SerializedName("colors")
    @Expose
    private List<ColorModel> colors;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(int offerPrice) {
        this.offerPrice = offerPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isIsOffer() {
        return isOffer;
    }

    public void setIsOffer(boolean isOffer) {
        this.isOffer = isOffer;
    }

    public boolean isIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public List<ImageModel> getImages() {
        return images;
    }

    public void setImages(List<ImageModel> images) {
        this.images = images;
    }

    public List<ColorModel> getColors() {
        return colors;
    }

    public void setColors(List<ColorModel> colors) {
        this.colors = colors;
    }
}
