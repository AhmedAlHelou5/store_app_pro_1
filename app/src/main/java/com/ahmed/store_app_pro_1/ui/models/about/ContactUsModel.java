package com.ahmed.store_app_pro_1.ui.models.about;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactUsModel {
    @SerializedName("name")
     @Expose
    String name;
    @SerializedName("email")
    @Expose
    String email;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("phone")
    @Expose
    String phone;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
