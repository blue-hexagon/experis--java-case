package com;

import com.product.Product;
import com.product.ProductReader;
import com.recommender.ProductSuggester;
import com.recommender.SubsetFinder;
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

        for (UserSession session : UserSessionReader.getSessionList()) {
            ProductSuggester.getSuggestion(session);
            System.out.println(session.toString());

        }
        ArrayList<Integer> hotPurchases = SubsetFinder.FindUserPurchasesUnionSet(UserReader.getUserList());
//        hotPurchases.reduce
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
