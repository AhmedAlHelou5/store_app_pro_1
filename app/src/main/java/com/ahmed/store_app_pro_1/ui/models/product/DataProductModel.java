package com.ahmed.store_app_pro_1.ui.models.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataProductModel {
    @SerializedName("product")
    @Expose
    private ProductModel product;
    @SerializedName("recommended")
    @Expose
    private List<ProductModel> recommended;

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public List<ProductModel> getRecommended() {
        return recommended;
    }

    public void setRecommended(List<ProductModel> recommended) {
        this.recommended = recommended;
    }

}
