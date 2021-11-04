package com;

import com.product.Product;
import com.recommender.HotProductFinder;
import com.session.UserSession;
import com.user.User;


public class Main {
    private static final boolean DEBUG = false;

    public static void main(String[] args) {
        try {
            CSVReader.ReadObjects();
        } catch (AlreadyInitializedException e) {
            e.printStackTrace();
        }
        if (DEBUG) {
            DumpDataObjects();
        }

        System.out.println(HotProductFinder.GenerateOftenBoughtProductsList(CSVReader.getUserList(),3));
    }

    private static void DumpDataObjects() {
        System.out.printf("%-15s %-30s %-15s%n", "---", "Printing Products", "---");
        for (Product product : CSVReader.getProductList()) {
            System.out.println(product.toString());
        }

        System.out.printf("%n%n%-15s %-30s %-15s%n", "---", "Printing Users", "---");
        for (User user : CSVReader.getUserList()) {
            System.out.println(user.toString());
        }

        System.out.printf("%n%n%-15s %-30s %-15s%n", "---", "Printing Sessions", "---");
        for (UserSession userSession : CSVReader.getUserSessionList()) {
            System.out.println(userSession.toString());
        }
    }

}
