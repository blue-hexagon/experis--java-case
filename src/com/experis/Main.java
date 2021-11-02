package com.experis;

import com.model.product.Product;
import com.model.product.ProductReader;
import com.model.user.User;
import com.model.user.UserReader;

public class Main {
    public static void main(String[] args) {
        ProductReader productReader = new ProductReader();
        UserReader userReader = new UserReader();
        for (Product product : ProductReader.getProductList()) {
//            System.out.println(product.toString());
        }
        for (User user : UserReader.getUserList()) {
            System.out.println(user.toString());
        }
    }
}
