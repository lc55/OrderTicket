package com.lchao.enums;

public enum UserType {

    admin(1),
    user(2);
    private final int type;

    UserType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
