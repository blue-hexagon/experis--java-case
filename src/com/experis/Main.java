package com.experis;

import com.model.Product;
import com.model.ProductFieldSpanRecord;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.List;


public class Main {
    String basePath = new File("").getAbsolutePath();

    public static void main(String[] args) {
        List<File> csvDataFiles = Arrays.asList(new File("src/com/data/CurrentUserSession.txt"), new File("src/com/data/Users.txt"));

        ArrayList<Product> products = new ArrayList<>();
        productCreator(products, new File("src/com/data/Products.txt"));
        for (Product product : products) {
            System.out.println(product.getRating());
        }
    }

    private static boolean isBetween(int startIndex, int endIndex, int index) {
        return startIndex <= index && endIndex >= index;

    }

    private static void productCreator(List<Product> products, File csvFile) {
        try {
            Scanner csvScanner = new Scanner(csvFile);
            while (csvScanner.hasNextLine()) {
                Product product = new Product();
                String[] csvField = csvScanner.nextLine().split("[,]");
                ArrayList<String> categories = new ArrayList<>();
                for (int iterValue = 0; iterValue < ProductFieldSpanRecord.RANGE.fieldEndPosition(); iterValue++) {
                    String dataField = csvField[iterValue].strip();
                    System.out.println(dataField);
                    if (dataField.length() > 0) {
                        if (isBetween(ProductFieldSpanRecord.ID.fieldStartPosition(), ProductFieldSpanRecord.ID.fieldEndPosition(), iterValue)) {
                            product.setId(Integer.parseInt(dataField));
                        } else if (isBetween(ProductFieldSpanRecord.TITLE.fieldStartPosition(), ProductFieldSpanRecord.TITLE.fieldEndPosition(), iterValue)) {
                            product.setTitle(dataField);
                        } else if (isBetween(ProductFieldSpanRecord.RELEASE_YEAR.fieldStartPosition(), ProductFieldSpanRecord.RELEASE_YEAR.fieldEndPosition(), iterValue)) {
                            product.setReleaseYear(Integer.parseInt(dataField));
                        } else if (isBetween(ProductFieldSpanRecord.CATEGORIES.fieldStartPosition(), ProductFieldSpanRecord.CATEGORIES.fieldEndPosition(), iterValue)) {
                            categories.add(dataField);
                        } else if (isBetween(ProductFieldSpanRecord.RATING.fieldStartPosition(), ProductFieldSpanRecord.RATING.fieldEndPosition(), iterValue)) {
                            product.setRating(Float.parseFloat(dataField));
                        } else if (isBetween(ProductFieldSpanRecord.PRICE.fieldStartPosition(), ProductFieldSpanRecord.PRICE.fieldEndPosition(), iterValue)) {
                            product.setPrice(Integer.parseInt(dataField));
                        }
                    }
                }
                product.setCategories(categories);
                products.add(product);
            }
            csvScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }
}
