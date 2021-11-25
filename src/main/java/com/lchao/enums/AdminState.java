package com.lchao.enums;

public enum AdminState {
    // 管理员状态
    disable(0),
    enable(1);

    private final int state;

    AdminState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
