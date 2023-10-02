package com.ahmed.store_app_pro_1.ui.models.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CategoryMainModel implements Serializable {
    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("data")
    @Expose
    private List<CategoryModel> data;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("message")
    @Expose
    private Object message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<CategoryModel> getData() {
        return data;
    }

    public void setData(List<CategoryModel> data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}