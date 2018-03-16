package com.arcanum.arcanumstoremanager.domain.entity;

/**
 * Created by norman on 24/01/18.
 */

public class Product {
    private long code;
    private String name;
    private double price;
    private double cost;
    private int stock;
    private boolean demoAvailable;

    public long getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isDemoAvailable() {
        return demoAvailable;
    }

    public void setDemoAvailable(boolean demoAvailable) {
        this.demoAvailable = demoAvailable;
    }
}
