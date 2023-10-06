package com.ahmed.store_app_pro_1.ui.models.orders;

import com.ahmed.store_app_pro_1.ui.models.OrderModel;
import com.ahmed.store_app_pro_1.ui.models.images.ImageModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderItemModel extends OrderModel implements Serializable  {
    @SerializedName("product_id")
    @Expose
    private  int productId;
    @SerializedName("product_name")
    @Expose
    private  String name;

    @SerializedName("price")
    @Expose
    private  double price;
    @SerializedName("images")
    @Expose
    private List<ImageModel> images;

    @SerializedName("quantity")
    @Expose
    private  int quantity;

    public OrderItemModel(int productId, String name, double price, List<ImageModel> images, int quantity) {
        super();
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.images = images;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<ImageModel> getImages() {
        return images;
    }

    public void setImages(List<ImageModel> images) {
        this.images = images;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
