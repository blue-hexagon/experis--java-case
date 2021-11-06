package com.session;

import com.IReadable;
import com.MathUtils;
import com.product.ProductList;
import com.user.UserList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserSessionList implements IReadable {
    static final ArrayList<UserSession> userSessionList = new ArrayList<>();
    static final File CSV_FILE_USER_SESSIONS = new File("src/com/data/CurrentUserSession.txt");

    public static ArrayList<UserSession> getList() {
        return userSessionList;
    }

    @Override
    public void read() throws FileNotFoundException {
        if (!userSessionList.isEmpty()) {
            userSessionList.clear();
        }
        Scanner csvScanner = new Scanner(CSV_FILE_USER_SESSIONS);
        while (csvScanner.hasNextLine()) {
            while (csvScanner.hasNextLine()) {
                UserSession userSession = new UserSession();
                String[] csvFieldArray = csvScanner.nextLine().split("[,]");
                for (int fieldPosition = UserSessionFieldSpanRecord.RANGE.fieldStartPosition(); fieldPosition <= UserSessionFieldSpanRecord.RANGE.fieldEndPosition(); fieldPosition++) {
                    String fieldData = csvFieldArray[fieldPosition].strip();
                    if (fieldData.length() > 0) {
                        if (MathUtils.isBetween(fieldPosition, UserSessionFieldSpanRecord.USER_ID.fieldStartPosition(), UserSessionFieldSpanRecord.USER_ID.fieldEndPosition())) {
                            userSession.setUser(
                                    UserList.getList().stream()
                                            .filter(user -> Integer.parseInt(fieldData) == (user.getId()))
                                            .findAny()
                                            .orElse(null)
                            );
                        } else if (MathUtils.isBetween(fieldPosition, UserSessionFieldSpanRecord.PRODUCT_ID.fieldStartPosition(), UserSessionFieldSpanRecord.PRODUCT_ID.fieldEndPosition())) {
                            userSession.setProduct(
                                    ProductList.getList().stream()
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
        csvScanner.close();
    }
}
