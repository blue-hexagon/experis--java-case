package com.product;

import java.text.MessageFormat;
import java.util.ArrayList;

public class Product {
    private int id;
    private String title;
    private int releaseYear;
    private final ArrayList<String> categories;
    private float rating;
    private int price;


    public Product() {
        categories = new ArrayList<>();
    }

    @Override
    public String toString() {
        return MessageFormat.format("Product'{'id={0}, title={1}, releaseYear={2}, categories={3}, rating={4}, price={5}'}'", id, title, releaseYear, categories, rating, price);
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
        return this.categories;
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