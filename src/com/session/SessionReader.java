package com.session;

import com.MathHelper;
import com.product.Product;
import com.session.SessionFieldSpanRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SessionReader {
    private static final File CSV_FILE = new File("src/com/data/CurrentUserSession.txt");
    private static final ArrayList<Product> productList = new ArrayList<>();
    public SessionReader() {
        try {
            Scanner csvScanner = new Scanner(CSV_FILE);
            while (csvScanner.hasNextLine()) {
                Session session = new Session();
                String[] csvFieldArray = csvScanner.nextLine().split("[,]");
                for (int iterValue = SessionFieldSpanRecord.RANGE.fieldStartPosition(); iterValue < SessionFieldSpanRecord.RANGE.fieldEndPosition(); iterValue++) {
                    String fieldData = csvFieldArray[iterValue].strip();
                    if (fieldData.length() > 0) {
                        if (MathHelper.isBetween(iterValue, SessionFieldSpanRecord.USER_ID.fieldStartPosition(), SessionFieldSpanRecord.USER_ID.fieldEndPosition()))
                            session.setUserId(Integer.parseInt(fieldData));
                        else if (MathHelper.isBetween(iterValue, SessionFieldSpanRecord.PRODUCT_ID.fieldStartPosition(), SessionFieldSpanRecord.PRODUCT_ID.fieldEndPosition()))
                            session.setProductId(fieldData);
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
