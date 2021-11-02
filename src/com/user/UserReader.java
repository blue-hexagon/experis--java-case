package com.user;

import com.MathHelper;
import com.product.ProductReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserReader {
    private static final File CSV_FILE = new File("src/com/data/Users.txt");
    private static final ArrayList<User> userList = new ArrayList<>();

    public UserReader() {
        try {
            Scanner csvScanner = new Scanner(UserReader.CSV_FILE);
            while (csvScanner.hasNextLine()) {
                User user = new User();
                String[] csvFieldArray = csvScanner.nextLine().split("[,]");
                for (int iterValue = SessionFieldSpanRecord.RANGE.fieldStartPosition(); iterValue < SessionFieldSpanRecord.RANGE.fieldEndPosition(); iterValue++) {
                    String fieldData = csvFieldArray[iterValue].strip();
                    if (fieldData.length() > 0) {
                        if (MathHelper.isBetween(iterValue, SessionFieldSpanRecord.ID.fieldStartPosition(), SessionFieldSpanRecord.ID.fieldEndPosition()))
                            user.setId(Integer.parseInt(fieldData));
                        else if (MathHelper.isBetween(iterValue, SessionFieldSpanRecord.NAME.fieldStartPosition(), SessionFieldSpanRecord.NAME.fieldEndPosition()))
                            user.setName(fieldData);
                        else if (MathHelper.isBetween(iterValue, SessionFieldSpanRecord.VIEWED.fieldStartPosition(), SessionFieldSpanRecord.VIEWED.fieldEndPosition())) {
                            for (String field : fieldData.split("[;]")) {
                                user.getViewedProducts().add(ProductReader.getProductList().get(Integer.parseInt(field)-1));
                            }
                        } else if (MathHelper.isBetween(iterValue, SessionFieldSpanRecord.PURCHASED.fieldStartPosition(), SessionFieldSpanRecord.PURCHASED.fieldEndPosition())) {
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

    public static ArrayList<User> getUserList() {
        return userList;
    }

}
