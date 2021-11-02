package com.model.user;

import com.experis.MathHelper;
import com.model.product.Product;
import com.model.product.ProductReader;
import com.model.user.UserFieldSpanRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserReader {
    private static final File CSV_FILE = new File("src/com/data/Users.txt");
    private static final ArrayList<User> userList = new ArrayList<>();

    public UserReader() {
        createUserList();
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }

    private void createUserList() {
        try {
            Scanner csvScanner = new Scanner(UserReader.CSV_FILE);
            while (csvScanner.hasNextLine()) {
                User user = new User();
                String[] csvFieldArray = csvScanner.nextLine().split("[,]");
                for (int iterValue = UserFieldSpanRecord.RANGE.fieldStartPosition(); iterValue < UserFieldSpanRecord.RANGE.fieldEndPosition(); iterValue++) {
                    String fieldData = csvFieldArray[iterValue].strip();
                    if (fieldData.length() > 0) {
                        if (MathHelper.isBetween(iterValue, UserFieldSpanRecord.ID.fieldStartPosition(), UserFieldSpanRecord.ID.fieldEndPosition()))
                            user.setId(Integer.parseInt(fieldData));
                        else if (MathHelper.isBetween(iterValue, UserFieldSpanRecord.NAME.fieldStartPosition(), UserFieldSpanRecord.NAME.fieldEndPosition()))
                            user.setName(fieldData);
                        else if (MathHelper.isBetween(iterValue, UserFieldSpanRecord.VIEWED.fieldStartPosition(), UserFieldSpanRecord.VIEWED.fieldEndPosition())) {
                            for (String field : fieldData.split("[;]")) {
                                user.getViewedProducts().add(ProductReader.getProductList().get(Integer.parseInt(field)-1));
                            }
                        } else if (MathHelper.isBetween(iterValue, UserFieldSpanRecord.PURCHASED.fieldStartPosition(), UserFieldSpanRecord.PURCHASED.fieldEndPosition())) {
                            for (String field : fieldData.split("[;]")) {
                                user.getPurchasedProducts().add(ProductReader.getProductList().get(Integer.parseInt(field)-1));
                            }
                        }
                    }
                }
                getUserList().add(user);
            }
            csvScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }
}
