package com;

import com.product.Product;
import com.recommender.ProductSuggester;
import com.recommender.SubsetFinder;
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

        for (UserSession session : CSVReader.getUserSessionList()) {
            ProductSuggester.getSuggestion(session);
            System.out.println(session.toString());

        }
//        ArrayList<Integer> hotPurchases = SubsetFinder.FindUserPurchasesUnionSet(UserReader.getUserList());
//        hotPurchases.reduce
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
