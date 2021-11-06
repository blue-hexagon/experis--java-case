package com;

import com.product.Product;
import com.product.ProductList;
import com.session.UserSession;
import com.session.UserSessionList;
import com.user.User;
import com.user.UserList;

import java.io.FileNotFoundException;


public class Main {
    private static final boolean DEBUG = false;

    public static void main(String[] args) {
        IReadable products = new ProductList();
        IReadable users = new UserList();
        IReadable userSessions = new UserSessionList();
        try {
            for (IReadable readable : new IReadable[]{products, users, userSessions}) {
                readable.read();
            }
            if (DEBUG)
                DumpDataObjects();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        System.out.println(ProductList.getProductList());
//        System.out.println(HotProductFinder.GenerateOftenBoughtProductsList(CSVReader.getUserList(),3));
    }

    private static void DumpDataObjects() {
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
