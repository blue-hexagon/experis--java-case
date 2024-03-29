package com.user;

public enum UserFieldSpanRecord {
    ID(0, 0),
    NAME(1, 1),
    VIEWED(2, 2),
    PURCHASED(3, 3),
    RANGE(0, 3);

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