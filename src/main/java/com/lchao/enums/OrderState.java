package com.lchao.enums;

public enum OrderState {

    // 订单状态
    unpaid(0),
    paid(1),
    cancel(2);

    private final int state;

    OrderState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
