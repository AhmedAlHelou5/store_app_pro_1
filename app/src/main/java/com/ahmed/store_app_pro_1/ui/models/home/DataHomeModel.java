package com.ahmed.store_app_pro_1.ui.models.home;

import com.ahmed.store_app_pro_1.ui.models.category.CategoryModel;
import com.ahmed.store_app_pro_1.ui.models.category.MostSoldProductModel;
import com.ahmed.store_app_pro_1.ui.models.product.ProductModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DataHomeModel  implements Serializable {


    @SerializedName("mostSoldProduct")
    @Expose
    private ProductModel mostSoldProduct;
    @SerializedName("sliders")
    @Expose
    private List<SliderHomeModel> sliders;
    @SerializedName("categories")
    @Expose
    private List<CategoryModel> categories;
    @SerializedName("offers")
    @Expose
    private List<ProductModel> offers;
    @SerializedName("products")
    @Expose
    private List<ProductModel> products;

    public ProductModel getMostSoldProduct() {
        return mostSoldProduct;
    }

    public void setMostSoldProduct(ProductModel mostSoldProduct) {
        this.mostSoldProduct = mostSoldProduct;
    }

    public List<SliderHomeModel> getSliders() {
        return sliders;
    }

    public void setSliders(List<SliderHomeModel> sliders) {
        this.sliders = sliders;
    }

    public List<CategoryModel> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryModel> categories) {
        this.categories = categories;
    }

    public List<ProductModel> getOffers() {
        return offers;
    }

    public void setOffers(List<ProductModel> offers) {
        this.offers = offers;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }


}
