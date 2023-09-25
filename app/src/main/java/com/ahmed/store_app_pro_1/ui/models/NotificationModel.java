package com.ahmed.store_app_pro_1.ui.models;

public class NotificationModel {
    private String date;
    private String title;
    private String description;

    public NotificationModel(String date, String description) {
        this.date = date;
        this.description = description;
    }

    public NotificationModel(String date, String title, String description) {
        this.date = date;
        this.title = title;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
