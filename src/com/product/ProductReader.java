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
                Scanner csvScanner = new Scanner(ProductReader.CSV_FILE);
                while (csvScanner.hasNextLine()) {
                    Product product = new Product();
                    String[] csvFieldArray = csvScanner.nextLine().split("[,]");
                    for (int iterValue = UserFieldSpanRecord.RANGE.fieldStartPosition(); iterValue < UserFieldSpanRecord.RANGE.fieldEndPosition(); iterValue++) {
                        String fieldData = csvFieldArray[iterValue].strip();
                        if (fieldData.length() > 0) {
                            if (MathHelper.isBetween(iterValue, UserFieldSpanRecord.ID.fieldStartPosition(), UserFieldSpanRecord.ID.fieldEndPosition()))
                                product.setId(Integer.parseInt(fieldData));
                            else if (MathHelper.isBetween(iterValue, UserFieldSpanRecord.TITLE.fieldStartPosition(), UserFieldSpanRecord.TITLE.fieldEndPosition()))
                                product.setTitle(fieldData);
                            else if (MathHelper.isBetween(iterValue, UserFieldSpanRecord.RELEASE_YEAR.fieldStartPosition(), UserFieldSpanRecord.RELEASE_YEAR.fieldEndPosition()))
                                product.setReleaseYear(Integer.parseInt(fieldData));
                            else if (MathHelper.isBetween(iterValue, UserFieldSpanRecord.CATEGORIES.fieldStartPosition(), UserFieldSpanRecord.CATEGORIES.fieldEndPosition()))
                                product.getCategories().add(fieldData);
                            else if (MathHelper.isBetween(iterValue, UserFieldSpanRecord.RATING.fieldStartPosition(), UserFieldSpanRecord.RATING.fieldEndPosition()))
                                product.setRating(Float.parseFloat(fieldData));
                            else if (MathHelper.isBetween(iterValue, UserFieldSpanRecord.PRICE.fieldStartPosition(), UserFieldSpanRecord.PRICE.fieldEndPosition()))
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

