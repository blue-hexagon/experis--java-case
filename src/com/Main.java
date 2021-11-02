package com;

import com.product.Product;
import com.product.ProductReader;
import com.session.UserSession;
import com.session.UserSessionReader;
import com.user.User;
import com.user.UserReader;

import java.util.ArrayList;

public class Main {
    private static final boolean DEBUG = false;

    public static void main(String[] args) {
        try {
            InitializeDataStructuresFromCSV();
        } catch (AlreadyInitializedException e) {
            e.printStackTrace();
        }
        if (DEBUG) {
            DumpDataObjects();
        }

        for (UserSession userSession : UserSessionReader.getSessionList()) {
            ArrayList<Product> viewedProducts = userSession.getUserId().getViewedProducts();
            ArrayList<Product> purchasedProducts = userSession.getUserId().getPurchasedProducts();
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
            System.out.println(lowestYear);
            System.out.println(lowestRating);
        }

    }

    private static void DumpDataObjects() {
        System.out.printf("%-15s %-30s %-15s%n", "---", "Printing Products", "---");
        for (Product product : ProductReader.getProductList()) {
            System.out.println(product.toString());
        }

        System.out.printf("%n%n%-15s %-30s %-15s%n", "---", "Printing Users", "---");
        for (User user : UserReader.getUserList()) {
            System.out.println(user.toString());
        }

        System.out.printf("%n%n%-15s %-30s %-15s%n", "---", "Printing Sessions", "---");
        for (UserSession userSession : UserSessionReader.getSessionList()) {
            System.out.println(userSession.toString());
        }
    }

    private static void InitializeDataStructuresFromCSV() throws AlreadyInitializedException {
        ProductReader.ReadProducts();
        UserReader.ReadUsers();
        UserSessionReader.ReadSessions();
    }
}
