package com.product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.AlreadyInitializedException;
import com.MathHelper;

public class ProductReader {
    private static final File CSV_FILE = new File("src/com/data/Products.txt");
    private static ArrayList<Product> productList = new ArrayList<>();

    public static ArrayList<Product> getProductList() {
        return productList;
    }

    public static void ReadProducts() throws AlreadyInitializedException {
        try {
            if (productList.size() == 0) {
                Scanner csvScanner = new Scanner(CSV_FILE);
                while (csvScanner.hasNextLine()) {
                    Product product = new Product();
                    String[] csvFieldArray = csvScanner.nextLine().split("[,]");
                    for (int iterValue = ProductFieldSpanRecord.RANGE.fieldStartPosition(); iterValue < ProductFieldSpanRecord.RANGE.fieldEndPosition(); iterValue++) {
                        String fieldData = csvFieldArray[iterValue].strip();
                        if (fieldData.length() > 0) {
                            if (MathHelper.isBetween(iterValue, ProductFieldSpanRecord.ID.fieldStartPosition(), ProductFieldSpanRecord.ID.fieldEndPosition()))
                                product.setId(Integer.parseInt(fieldData));
                            else if (MathHelper.isBetween(iterValue, ProductFieldSpanRecord.TITLE.fieldStartPosition(), ProductFieldSpanRecord.TITLE.fieldEndPosition()))
                                product.setTitle(fieldData);
                            else if (MathHelper.isBetween(iterValue, ProductFieldSpanRecord.RELEASE_YEAR.fieldStartPosition(), ProductFieldSpanRecord.RELEASE_YEAR.fieldEndPosition()))
                                product.setReleaseYear(Integer.parseInt(fieldData));
                            else if (MathHelper.isBetween(iterValue, ProductFieldSpanRecord.CATEGORIES.fieldStartPosition(), ProductFieldSpanRecord.CATEGORIES.fieldEndPosition()))
                                product.getCategories().add(fieldData);
                            else if (MathHelper.isBetween(iterValue, ProductFieldSpanRecord.RATING.fieldStartPosition(), ProductFieldSpanRecord.RATING.fieldEndPosition()))
                                product.setRating(Float.parseFloat(fieldData));
                            else if (MathHelper.isBetween(iterValue, ProductFieldSpanRecord.PRICE.fieldStartPosition(), ProductFieldSpanRecord.PRICE.fieldEndPosition()))
                                product.setPrice(Integer.parseInt(fieldData));
                        }
                    }
                    getProductList().add(product);
                }
                csvScanner.close();
            } else {
                throw new AlreadyInitializedException("Product list already initialized.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }


}

