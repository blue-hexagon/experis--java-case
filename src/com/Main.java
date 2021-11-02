package com;

import com.product.Product;
import com.product.ProductReader;
import com.user.User;
import com.user.UserReader;

public class Main {
    public static void main(String[] args) {
        ProductReader productReader = new ProductReader();
        UserReader userReader = new UserReader();
        for (Product product : ProductReader.getProductList()) {
            System.out.println(product.toString());
        }
        for (User user : UserReader.getUserList()) {
            System.out.println(user.toString());
        }

    }
}
