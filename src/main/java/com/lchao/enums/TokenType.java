package com.lchao.enums;

public enum TokenType {

    admin(1),
    user(2);
    private final int type;

    TokenType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
