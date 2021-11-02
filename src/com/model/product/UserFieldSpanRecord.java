package com.model.product;

public enum UserFieldSpanRecord {
    ID(0, 0),
    TITLE(1, 1),
    RELEASE_YEAR(2, 2),
    CATEGORIES(3, 7),
    RATING(8, 8),
    PRICE(9, 9),
    RANGE(0, 9 + 1); // Used for bounding the for-loop, hence the +1.

    private final int fieldStartPosition;
    private final int fieldEndPosition;

    UserFieldSpanRecord(int fieldStartPosition, int fieldEndPosition) {
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