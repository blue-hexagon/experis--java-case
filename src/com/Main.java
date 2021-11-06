package com;

import com.product.Product;
import com.product.ProductList;
import com.recommender.HotProductFinder;
import com.recommender.ProductSuggester;
import com.session.UserSession;
import com.session.UserSessionList;
import com.user.User;
import com.user.UserList;

import java.io.FileNotFoundException;


public class Main {
    private static final boolean DEBUG = true;

    public static void main(String[] args) {
        try {
            for (IReadable readable : new IReadable[]{new ProductList(), new UserList(), new UserSessionList()}) {
                readable.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (DEBUG)
            dumpParsedCSVObjects();

        HotProductFinder.generateHotProductsList(UserList.getUserList());

        /* Retrieve the 3 most purchased movies (pagesize param can be set to any value) */
        System.out.println(HotProductFinder.getMostPurchasedMovies(3));
        /* Retrieve the 3 highest rated movies */
        System.out.println(HotProductFinder.getHighestRatedMovies(3));

        for (UserSession session : UserSessionList.getUserSessionList()) {
            /* Get a related movie suggestion for a user viewing a product in the webshop. */
            ProductSuggester.getSuggestion(session);
        }
    }

    private static void dumpParsedCSVObjects() {
        System.out.printf("%-15s %-30s %-15s%n", "---", "Printing Products", "---");
        for (Product product : ProductList.getProductList()) {
            System.out.println(product.toString());
        }

        System.out.printf("%n%n%-15s %-30s %-15s%n", "---", "Printing Users", "---");
        for (User user : UserList.getUserList()) {
            System.out.println(user.toString());
        }

        System.out.printf("%n%n%-15s %-30s %-15s%n", "---", "Printing Sessions", "---");
        for (UserSession userSession : UserSessionList.getUserSessionList()) {
            System.out.println(userSession.toString());
        }
    }

}
