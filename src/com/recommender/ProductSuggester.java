package com.recommender;

import com.product.Product;
import com.session.UserSession;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductSuggester {
    /**
     * Takes a user session containing a user ID and a product they are currently viewing.
     * Returns a recommendation based on movies from the same category as the one product they are currently viewing.
     */
    public static void getSuggestion(UserSession userSession) {
//        ArrayList<Product> viewedProducts = userSession.getUser().getViewedProducts();
        List<String> userSessionProductCategories = userSession.getProduct().getCategories().stream().toList();
        ArrayList<Product> purchasedProducts = userSession.getUser().getPurchasedProducts();
        Set<String> purchasedProductCategories = new HashSet<String>();
        for (Product purchasedProduct : purchasedProducts) {
            purchasedProductCategories.addAll(purchasedProduct.getCategories());
        }
        System.out.println(("UserID(" + userSession.getUser().getId() + "): " + userSessionProductCategories + "\n           " + purchasedProductCategories));
//        System.out.println("UID: " + userSession.getUser().getId() + "; Year: " + lowestYear + "; Rating: " + lowestRating);

    }
}
