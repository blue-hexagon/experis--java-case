package com;

import com.product.Product;
import com.product.ProductReader;
import com.session.UserSession;
import com.session.UserSessionReader;
import com.user.User;
import com.user.UserReader;

public class Main {
    public static void main(String[] args) {
        ProductReader.ReadProducts();
        UserReader.ReadUsers();
        UserSessionReader.ReadSessions();
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
}
