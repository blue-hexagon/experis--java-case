package com.session;

public enum UserSessionFieldSpanRecord {
    USER_ID(0, 0),
    PRODUCT_ID(1, 1),
    RANGE(0, 1);

    private final int fieldStartPosition;
    private final int fieldEndPosition;

    UserSessionFieldSpanRecord(int fieldStartPosition, int fieldEndPosition) {
        this.fieldStartPosition = fieldStartPosition;
        this.fieldEndPosition = fieldEndPosition;
    }

    public int fieldStartPosition() {
        return fieldStartPosition;
    }

    public int fieldEndPosition() {
        return fieldEndPosition;
    }

}