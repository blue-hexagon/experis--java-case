package com;

import com.product.Product;
import com.product.ProductFieldSpanRecord;
import com.session.UserSession;
import com.session.UserSessionFieldSpanRecord;
import com.user.User;
import com.user.UserFieldSpanRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVReader {
    private static final File CSV_FILE_PRODUCTS = new File("src/com/data/Products.txt");
    private static final File CSV_FILE_USER_SESSIONS = new File("src/com/data/CurrentUserSession.txt");
    private static final File CSV_FILE_USERS = new File("src/com/data/Users.txt");
    private static final ArrayList<Product> productList = new ArrayList<>();
    private static final ArrayList<UserSession> userSessionList = new ArrayList<>();
    private static final ArrayList<User> userList = new ArrayList<>();

    public static ArrayList<Product> getProductList() {
        return productList;
    }

    public static ArrayList<UserSession> getUserSessionList() {
        return userSessionList;
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }

    public static class MathHelper {
        public static boolean isBetween(int index, int startIndex, int endIndex) {
            return startIndex <= index && endIndex >= index;
        }
    }

    public static void ReadObjects() throws AlreadyInitializedException {
        try {
            if (productList.isEmpty()) {
                Scanner csvScanner = new Scanner(CSV_FILE_PRODUCTS);
                while (csvScanner.hasNextLine()) {
                    ReadProducts(csvScanner);
                }
                csvScanner.close();
            } else {
                throw new AlreadyInitializedException("Productlist already initialized.");
            }
            if (userList.isEmpty()) {
                Scanner csvScanner = new Scanner(CSV_FILE_USERS);
                while (csvScanner.hasNextLine()) {
                    ReadUsers(csvScanner);
                }
                csvScanner.close();
            } else {
                throw new AlreadyInitializedException("Userlist already initialized.");
            }
            if (userSessionList.isEmpty()) {
                Scanner csvScanner = new Scanner(CSV_FILE_USER_SESSIONS);
                while (csvScanner.hasNextLine()) {
                    ReadUserSessions(csvScanner);
                }
                csvScanner.close();
            } else {
                throw new AlreadyInitializedException("Usersession list already initialized.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }

    private static void ReadUserSessions(Scanner csvScanner) {
        while (csvScanner.hasNextLine()) {
            UserSession userSession = new UserSession();
            String[] csvFieldArray = csvScanner.nextLine().split("[,]");
            for (int iterValue = UserSessionFieldSpanRecord.RANGE.fieldStartPosition(); iterValue <= UserSessionFieldSpanRecord.RANGE.fieldEndPosition(); iterValue++) {
                String fieldData = csvFieldArray[iterValue].strip();
                if (fieldData.length() > 0) {
                    if (MathHelper.isBetween(iterValue, UserSessionFieldSpanRecord.USER_ID.fieldStartPosition(), UserSessionFieldSpanRecord.USER_ID.fieldEndPosition())) {
                        userSession.setUser(
                                getUserList().stream()
                                        .filter(user -> Integer.parseInt(fieldData) == (user.getId()))
                                        .findAny()
                                        .orElse(null)
                        );
                    } else if (MathHelper.isBetween(iterValue, UserSessionFieldSpanRecord.PRODUCT_ID.fieldStartPosition(), UserSessionFieldSpanRecord.PRODUCT_ID.fieldEndPosition())) {
                        userSession.setProduct(
                                getProductList().stream()
                                        .filter(product -> Integer.parseInt(fieldData) == (product.getId()))
                                        .findAny()
                                        .orElse(null)
                        );
                    }
                }
            }
            userSessionList.add(userSession);
        }
    }

    private static void ReadProducts(Scanner csvScanner) {
        Product product = new Product();
        String[] csvFieldArray = csvScanner.nextLine().split("[,]");
        for (int iterValue = ProductFieldSpanRecord.RANGE.fieldStartPosition(); iterValue <= ProductFieldSpanRecord.RANGE.fieldEndPosition(); iterValue++) {
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
        productList.add(product);
    }


    private static void ReadUsers(Scanner csvScanner) {
        User user = new User();
        String[] csvFieldArray = csvScanner.nextLine().split("[,]");
        for (int iterValue = UserFieldSpanRecord.RANGE.fieldStartPosition(); iterValue <= UserFieldSpanRecord.RANGE.fieldEndPosition(); iterValue++) {
            String fieldData = csvFieldArray[iterValue].strip();
            if (fieldData.length() > 0) {
                if (MathHelper.isBetween(iterValue, UserFieldSpanRecord.ID.fieldStartPosition(), UserFieldSpanRecord.ID.fieldEndPosition()))
                    user.setId(Integer.parseInt(fieldData));
                else if (MathHelper.isBetween(iterValue, UserFieldSpanRecord.NAME.fieldStartPosition(), UserFieldSpanRecord.NAME.fieldEndPosition()))
                    user.setName(fieldData);
                else if (MathHelper.isBetween(iterValue, UserFieldSpanRecord.VIEWED.fieldStartPosition(), UserFieldSpanRecord.VIEWED.fieldEndPosition())) {
                    for (String field : fieldData.split("[;]")) {
                        user.getViewedProducts().add(getProductList().get(Integer.parseInt(field) - 1));
                    }
                } else if (MathHelper.isBetween(iterValue, UserFieldSpanRecord.PURCHASED.fieldStartPosition(), UserFieldSpanRecord.PURCHASED.fieldEndPosition())) {
                    for (String field : fieldData.split("[;]")) {
                        user.getPurchasedProducts().add(getProductList().get(Integer.parseInt(field) - 1));
                    }
                }
            }
        }
        userList.add(user);
    }


}
