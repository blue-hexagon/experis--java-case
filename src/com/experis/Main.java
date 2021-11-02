package com.experis;

import com.model.product.Product;
import com.model.product.ProductReader;

public class Main {
    public static void main(String[] args) {
        ProductReader productReader = new ProductReader();
        for (Product product : productReader.getProductList()) {
            System.out.println(product.getPrice());
        }
    }
}
