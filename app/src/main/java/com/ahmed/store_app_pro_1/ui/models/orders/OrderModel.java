package com.ahmed.store_app_pro_1.ui.models;

import com.ahmed.store_app_pro_1.ui.models.orders.OrderItemModel;
import com.ahmed.store_app_pro_1.ui.models.orders.StoreStatus;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderModel implements Serializable {
    @SerializedName("order_id")
    @Expose
    private  int id;
    @SerializedName("total")
    @Expose
    private  int total;
    @SerializedName("sub_total")
    @Expose
    private String subTotal;
    @SerializedName("discount")
    @Expose
    private int discount;
    @SerializedName("payment_method")
    @Expose
    private String payment_method;

    @SerializedName("order_item")
    @Expose
    private List<OrderItemModel> orderItemModels;

    @SerializedName("store_status")
    @Expose
    private StoreStatus status;

    public OrderModel() {

    }

    public List<OrderItemModel> getOrderItemModels() {
        return orderItemModels;
    }

    public void setOrderItemModels(List<OrderItemModel> orderItemModels) {
        this.orderItemModels = orderItemModels;
    }

    public OrderModel(List<OrderItemModel> orderItemModels) {
        this.orderItemModels = orderItemModels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }


    public StoreStatus getStatus() {
        return status;
    }

    public void setStatus(StoreStatus status) {
        this.status = status;
    }
}
