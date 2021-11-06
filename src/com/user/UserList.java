package com.user;

import com.IReadable;
import com.MathUtils;
import com.product.ProductList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserList implements IReadable {
    static final ArrayList<User> userList = new ArrayList<>();
    static final File CSV_FILE_USERS = new File("src/com/data/Users.txt");

    public static ArrayList<User> getUserList() {
        return userList;
    }

    @Override
    public void read() throws FileNotFoundException {
        if (!userList.isEmpty()) {
            userList.clear();
        }
        Scanner csvScanner = new Scanner(CSV_FILE_USERS);
        while (csvScanner.hasNextLine()) {
            User user = new User();
            String[] csvFieldArray = csvScanner.nextLine().split("[,]");
            for (int iterValue = UserFieldSpanRecord.RANGE.fieldStartPosition(); iterValue <= UserFieldSpanRecord.RANGE.fieldEndPosition(); iterValue++) {
                String fieldData = csvFieldArray[iterValue].strip();
                if (fieldData.length() > 0) {
                    if (MathUtils.isBetween(iterValue, UserFieldSpanRecord.ID.fieldStartPosition(), UserFieldSpanRecord.ID.fieldEndPosition()))
                        user.setId(Integer.parseInt(fieldData));
                    else if (MathUtils.isBetween(iterValue, UserFieldSpanRecord.NAME.fieldStartPosition(), UserFieldSpanRecord.NAME.fieldEndPosition()))
                        user.setName(fieldData);
                    else if (MathUtils.isBetween(iterValue, UserFieldSpanRecord.VIEWED.fieldStartPosition(), UserFieldSpanRecord.VIEWED.fieldEndPosition())) {
                        for (String field : fieldData.split("[;]")) {
                            user.getViewedProducts().add(ProductList.getProductList().get(Integer.parseInt(field) - 1));
                        }
                    } else if (MathUtils.isBetween(iterValue, UserFieldSpanRecord.PURCHASED.fieldStartPosition(), UserFieldSpanRecord.PURCHASED.fieldEndPosition())) {
                        for (String field : fieldData.split("[;]")) {
                            user.getPurchasedProducts().add(ProductList.getProductList().get(Integer.parseInt(field) - 1));
                        }
                    }
                }
                userList.add(user);
            }
        }
        csvScanner.close();

    }
}









