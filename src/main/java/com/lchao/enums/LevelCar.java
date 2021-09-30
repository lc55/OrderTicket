package com.lchao.enums;

public enum LevelCar {

    // 车厢级别
    econ(3),
    ordinal(2),
    luxury(1);

    private final int level;

    LevelCar(int level) {
        this.level = level;
    }

    public int getLevel(){
        return level;
    }
}
