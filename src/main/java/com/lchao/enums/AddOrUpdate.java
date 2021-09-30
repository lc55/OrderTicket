package com.lchao.enums;

public enum AddOrUpdate {
    ADD(1),
    UPDATE(2);

    int value;
    AddOrUpdate(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
