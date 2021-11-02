package com.recommender;

import com.product.Product;
import com.session.UserSession;

import java.util.ArrayList;

public class ProductSuggester {
    public static Product getSuggestion(UserSession userSession) {
        ArrayList<Product> viewedProducts = userSession.getUser().getViewedProducts();
        ArrayList<Product> purchasedProducts = userSession.getUser().getPurchasedProducts();
        int lowestYear = Integer.MAX_VALUE;
        float lowestRating = Integer.MAX_VALUE;
        for (Product product : viewedProducts) {
            if (product.getReleaseYear() < lowestYear)
                lowestYear = product.getReleaseYear();
            if (product.getRating() < lowestRating)
                lowestRating = product.getRating();
        }
        for (Product product : purchasedProducts) {
            if (product.getReleaseYear() < lowestYear)
                lowestYear = product.getReleaseYear();
            if (product.getRating() < lowestRating)
                lowestRating = product.getRating();
        }
        System.out.println("UID: " + userSession.getUser().getId() + "; Year: " + lowestYear + "; Rating: " + lowestRating);
        return new Product();
    }
}
