package com.lchao.enums;

public enum TrainType {

    // 车次类别
    D(1),
    G(2);

    private final int type;

    TrainType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
