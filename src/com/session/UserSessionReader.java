package com.session;

import com.MathHelper;
import com.product.ProductReader;
import com.user.UserReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserSessionReader {
    private static final File CSV_FILE = new File("src/com/data/CurrentUserSession.txt");
    private static final ArrayList<UserSession> USER_SESSION_LIST = new ArrayList<>();

    public static ArrayList<UserSession> getSessionList() {
        return USER_SESSION_LIST;
    }

    public static void ReadSessions() {
        try {
            Scanner csvScanner = new Scanner(CSV_FILE);
            while (csvScanner.hasNextLine()) {
                UserSession userSession = new UserSession();
                String[] csvFieldArray = csvScanner.nextLine().split("[,]");
                for (int iterValue = UserSessionFieldSpanRecord.RANGE.fieldStartPosition(); iterValue < UserSessionFieldSpanRecord.RANGE.fieldEndPosition(); iterValue++) {
                    String fieldData = csvFieldArray[iterValue].strip();
                    if (fieldData.length() > 0) {
                        if (MathHelper.isBetween(iterValue, UserSessionFieldSpanRecord.USER_ID.fieldStartPosition(), UserSessionFieldSpanRecord.USER_ID.fieldEndPosition())) {
                            userSession.setUserId(UserReader
                                    .getUserList().stream()
                                    .filter(user -> Integer.parseInt(fieldData) == (user.getId()))
                                    .findAny()
                                    .orElse(null)
                            );
                        } else if (MathHelper.isBetween(iterValue, UserSessionFieldSpanRecord.PRODUCT_ID.fieldStartPosition(), UserSessionFieldSpanRecord.PRODUCT_ID.fieldEndPosition())) {
                            userSession.setProductId(ProductReader
                                    .getProductList().stream()
                                    .filter(product -> Integer.parseInt(fieldData) == (product.getId()))
                                    .findAny()
                                    .orElse(null)
                            );
                        }
                    }
                }
                USER_SESSION_LIST.add(userSession);
            }
            csvScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }
}
