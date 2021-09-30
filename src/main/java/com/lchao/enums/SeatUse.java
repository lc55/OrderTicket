package com.lchao.enums;

public enum SeatUse {

    YES(1),
    NO(0);
    private final int value;

    SeatUse(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
