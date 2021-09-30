package com.lchao.enums;

public enum TrainState {

    // 车次状态
    disable(0),
    enable(1);

    private final int state;

    TrainState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
