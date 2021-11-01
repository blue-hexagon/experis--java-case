package com.model;

import java.util.ArrayList;
import java.util.List;



public class Product {
    int id;
    String title;
    int releaseYear;
    ArrayList<String> categories;
    float rating;
    int price;

    public Product() {

    }

    public Product(int id, String title, int releaseYear, ArrayList<String> categories, float rating, int price) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.categories = categories;
        this.rating = rating;
        this.price = price;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

