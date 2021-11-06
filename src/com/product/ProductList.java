package com.product;

import com.AlreadyInitializedException;
import com.IReadable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.MathUtils;

public class ProductList implements IReadable {
    static final ArrayList<Product> productList = new ArrayList<>();
    static final File CSV_FILE_PRODUCTS = new File("src/com/data/Products.txt");


    public static ArrayList<Product> getProductList() {
        return productList;
    }

    @Override
    public void read() throws FileNotFoundException {
        if (!productList.isEmpty()) {
            productList.clear();
        }
        Scanner csvScanner = new Scanner(CSV_FILE_PRODUCTS);
        while (csvScanner.hasNextLine()) {
            Product product = new Product();
            String[] csvFieldArray = csvScanner.nextLine().split("[,]");
            for (int iterValue = ProductFieldSpanRecord.RANGE.fieldStartPosition(); iterValue <= ProductFieldSpanRecord.RANGE.fieldEndPosition(); iterValue++) {
                String fieldData = csvFieldArray[iterValue].strip();
                if (fieldData.length() > 0) {
                    if (MathUtils.isBetween(iterValue, ProductFieldSpanRecord.ID.fieldStartPosition(), ProductFieldSpanRecord.ID.fieldEndPosition()))
                        product.setId(Integer.parseInt(fieldData));
                    else if (MathUtils.isBetween(iterValue, ProductFieldSpanRecord.TITLE.fieldStartPosition(), ProductFieldSpanRecord.TITLE.fieldEndPosition()))
                        product.setTitle(fieldData);
                    else if (MathUtils.isBetween(iterValue, ProductFieldSpanRecord.RELEASE_YEAR.fieldStartPosition(), ProductFieldSpanRecord.RELEASE_YEAR.fieldEndPosition()))
                        product.setReleaseYear(Integer.parseInt(fieldData));
                    else if (MathUtils.isBetween(iterValue, ProductFieldSpanRecord.CATEGORIES.fieldStartPosition(), ProductFieldSpanRecord.CATEGORIES.fieldEndPosition()))
                        product.getCategories().add(fieldData);
                    else if (MathUtils.isBetween(iterValue, ProductFieldSpanRecord.RATING.fieldStartPosition(), ProductFieldSpanRecord.RATING.fieldEndPosition()))
                        product.setRating(Float.parseFloat(fieldData));
                    else if (MathUtils.isBetween(iterValue, ProductFieldSpanRecord.PRICE.fieldStartPosition(), ProductFieldSpanRecord.PRICE.fieldEndPosition()))
                        product.setPrice(Integer.parseInt(fieldData));
                }
            }
            productList.add(product);
        }
        csvScanner.close();
    }
}









