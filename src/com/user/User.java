package com.user;

import com.product.Product;

import java.text.MessageFormat;
import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private ArrayList<Product> viewedProducts;
    private ArrayList<Product> purchasedProducts;

    @Override
    public String toString() {
        return MessageFormat.format("User'{'id={0}, name={1}, viewedProducts={2}, purchasedProducts={3}'}'", id, name, viewedProducts, purchasedProducts);
    }

    public User() {
        viewedProducts = new ArrayList<>();
        purchasedProducts = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getViewedProducts() {
        return viewedProducts;
    }

    public void setViewedProducts(ArrayList<Product> viewedProducts) {
        this.viewedProducts = viewedProducts;
    }

    public ArrayList<Product> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(ArrayList<Product> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }


}