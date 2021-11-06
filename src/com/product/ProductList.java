package com.product;

import com.IReadable;
import com.MathUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductList implements IReadable {
    static final ArrayList<Product> productList = new ArrayList<>();
    static final File CSV_FILE_PRODUCTS = new File("src/com/data/Products.txt");

    public static ArrayList<Product> getList() {
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
            for (int fieldPosition = ProductFieldSpanRecord.RANGE.fieldStartPosition(); fieldPosition <= ProductFieldSpanRecord.RANGE.fieldEndPosition(); fieldPosition++) {
                String fieldData = csvFieldArray[fieldPosition].strip();
                if (fieldData.length() > 0) {
                    if (MathUtils.isBetween(fieldPosition, ProductFieldSpanRecord.ID.fieldStartPosition(), ProductFieldSpanRecord.ID.fieldEndPosition()))
                        product.setId(Integer.parseInt(fieldData));
                    else if (MathUtils.isBetween(fieldPosition, ProductFieldSpanRecord.TITLE.fieldStartPosition(), ProductFieldSpanRecord.TITLE.fieldEndPosition()))
                        product.setTitle(fieldData);
                    else if (MathUtils.isBetween(fieldPosition, ProductFieldSpanRecord.RELEASE_YEAR.fieldStartPosition(), ProductFieldSpanRecord.RELEASE_YEAR.fieldEndPosition()))
                        product.setReleaseYear(Integer.parseInt(fieldData));
                    else if (MathUtils.isBetween(fieldPosition, ProductFieldSpanRecord.CATEGORIES.fieldStartPosition(), ProductFieldSpanRecord.CATEGORIES.fieldEndPosition()))
                        product.getCategories().add(fieldData);
                    else if (MathUtils.isBetween(fieldPosition, ProductFieldSpanRecord.RATING.fieldStartPosition(), ProductFieldSpanRecord.RATING.fieldEndPosition()))
                        product.setRating(Float.parseFloat(fieldData));
                    else if (MathUtils.isBetween(fieldPosition, ProductFieldSpanRecord.PRICE.fieldStartPosition(), ProductFieldSpanRecord.PRICE.fieldEndPosition()))
                        product.setPrice(Integer.parseInt(fieldData));
                }
            }
            productList.add(product);
        }
        csvScanner.close();
    }
}









