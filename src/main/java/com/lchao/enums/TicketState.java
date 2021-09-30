package com.lchao.enums;

public enum TicketState {
    YES(1),
    NO(2);
    private final int state;

    TicketState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
