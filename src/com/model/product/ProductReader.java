package com.model.product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class ProductReader {
    private static final File CSV_FILE = new File("src/com/data/Products.txt");
    private final ArrayList<Product> productList;

    public ProductReader() {
        productList = new ArrayList<>();
        createProductList();
    }

    private static boolean isBetween(int index, int startIndex, int endIndex) {
        return startIndex <= index && endIndex >= index;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void createProductList() {
        try {
            Scanner csvScanner = new Scanner(ProductReader.CSV_FILE);
            while (csvScanner.hasNextLine()) {
                Product product = new Product();
                String[] csvFieldArray = csvScanner.nextLine().split("[,]");
                ArrayList<String> categories = new ArrayList<>();
                for (int iterValue = 0; iterValue < ProductFieldSpanRecord.RANGE.fieldEndPosition(); iterValue++) {
                    String dataField = csvFieldArray[iterValue].strip();
//                    System.out.println(dataField);
                    if (dataField.length() > 0) {
                        if (isBetween(iterValue, ProductFieldSpanRecord.ID.fieldStartPosition(), ProductFieldSpanRecord.ID.fieldEndPosition()))
                            product.setId(Integer.parseInt(dataField));
                        else if (isBetween(iterValue, ProductFieldSpanRecord.TITLE.fieldStartPosition(), ProductFieldSpanRecord.TITLE.fieldEndPosition()))
                            product.setTitle(dataField);
                        else if (isBetween(iterValue, ProductFieldSpanRecord.RELEASE_YEAR.fieldStartPosition(), ProductFieldSpanRecord.RELEASE_YEAR.fieldEndPosition()))
                            product.setReleaseYear(Integer.parseInt(dataField));
                        else if (isBetween(iterValue, ProductFieldSpanRecord.CATEGORIES.fieldStartPosition(), ProductFieldSpanRecord.CATEGORIES.fieldEndPosition()))
                            categories.add(dataField);
                        else if (isBetween(iterValue, ProductFieldSpanRecord.RATING.fieldStartPosition(), ProductFieldSpanRecord.RATING.fieldEndPosition()))
                            product.setRating(Float.parseFloat(dataField));
                        else if (isBetween(iterValue, ProductFieldSpanRecord.PRICE.fieldStartPosition(), ProductFieldSpanRecord.PRICE.fieldEndPosition()))
                            product.setPrice(Integer.parseInt(dataField));
                    }
                }
                product.setCategories(categories);
                this.getProductList().add(product);
            }
            csvScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }
}

