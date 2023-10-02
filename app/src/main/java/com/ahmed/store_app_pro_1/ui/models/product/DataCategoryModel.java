package com.ahmed.store_app_pro_1.ui.models.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DataCategoryModel implements Serializable {

    private List<ProductModel> productModel;

    public  List<ProductModel> getProductModel() {
        return productModel;
    }

    public void setProductModel(List<ProductModel> productModel) {
        this.productModel = productModel;
    }
}
