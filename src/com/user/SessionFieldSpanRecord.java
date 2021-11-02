package com.user;

public enum SessionFieldSpanRecord {
    ID(0, 0),
    NAME(1, 1),
    VIEWED(2, 2),
    PURCHASED(3, 3),
    RANGE(0, 3 + 1); // Used for bounding the for-loop, hence the +1.

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