package com.ahmed.store_app_pro_1.ui.models;

public class OrderModel {
    private  int id;
    private String status;
    private double total;
    private String city;
    private double dicount;
    private double totalAfterDicount;

    public OrderModel(int id, String status, double total, String city, double dicount, double totalAfterDicount) {
        this.id = id;
        this.status = status;
        this.total = total;
        this.city = city;
        this.dicount = dicount;
        this.totalAfterDicount = totalAfterDicount;
    }

    public OrderModel(int id, String status, double total, String city, double dicount) {
        this.id = id;
        this.status = status;
        this.total = total;
        this.city = city;
        this.dicount = dicount;
    }

    public OrderModel(int id, String status, double total, String city) {
        this.id = id;
        this.status = status;
        this.total = total;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getDicount() {
        return dicount;
    }

    public void setDicount(double dicount) {
        this.dicount = dicount;
    }

    public double getTotalAfterDicount() {
        return totalAfterDicount;
    }

    public void setTotalAfterDicount(double totalAfterDicount) {
        this.totalAfterDicount = totalAfterDicount;
    }
}
