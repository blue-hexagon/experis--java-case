package com.recommender;

import com.product.Product;
import com.product.ProductList;
import com.session.UserSession;

import java.util.*;

public class ProductSuggester {
    /**
     * This methods creates movie suggestions based on the currently viewed product.
     *
     * It first reads through all movies the user has purchased and collect their categories
     * and creates a K,V map leading to the most bought movie based on category. It then finds the highest rated movies of that category.
     * If the movie is not already purchased by the user it is returned as a suggestion.
     */
    public static void getSuggestion(UserSession userSession, int pageSize) {
        List<String> userSessionViewedProductCategories = userSession.getProduct().getCategories().stream().toList();
        ArrayList<Product> usersPurchasedProducts = userSession.getUser().getPurchasedProducts();
        Set<String> purchasedProductCategories = new HashSet<String>();
        Map<String, Integer> sortedCategoriesToSelectAMovieFrom = new HashMap<>();

        for (Product purchasedProduct : usersPurchasedProducts) {
            purchasedProductCategories.addAll(purchasedProduct.getCategories());
        }
//        System.out.println(("UserID(" + userSession.getUser().getId() + "): " + userSessionViewedProductCategories + "\n           " + purchasedProductCategories));
        for (Product product : ProductList.getList()) {
            product.getCategories().forEach(s -> {
                if (purchasedProductCategories.contains(s)) {
                    if (sortedCategoriesToSelectAMovieFrom.containsKey(s)) {
                        sortedCategoriesToSelectAMovieFrom.put(s, sortedCategoriesToSelectAMovieFrom.get(s) + 1);
                    } else {
                        sortedCategoriesToSelectAMovieFrom.put(s, 1);
                    }
                }
            });
        }
        sortedCategoriesToSelectAMovieFrom.forEach((s, integer) -> {

        });
        System.out.println("UserID(" + userSession.getUser().getId() + "):" + sortedCategoriesToSelectAMovieFrom);
    }
}
