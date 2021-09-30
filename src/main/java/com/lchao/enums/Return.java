package com.lchao.enums;

public enum Return {

    // 是否返程
    no(0),
    yes(1);

    private final int value;

    Return(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
