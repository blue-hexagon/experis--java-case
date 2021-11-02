package com.session;

public enum SessionFieldSpanRecord {
    USER_ID(0, 0),
    PRODUCT_ID(1, 1),
    RANGE(0, 1 + 1); // Used for bounding the for-loop, hence the +1.

    private final int fieldStartPosition;
    private final int fieldEndPosition;

    SessionFieldSpanRecord(int fieldStartPosition, int fieldEndPosition) {
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