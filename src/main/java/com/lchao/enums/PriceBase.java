package com.lchao.enums;

public enum PriceBase {
    econ(3, 5L),
    ordinal(2, 10L),
    luxury(1, 15L);

    private final int type;
    private final Long priceBase;

    PriceBase(int type, Long priceBase) {
        this.type = type;
        this.priceBase = priceBase;
    }

    public Long getPriceBase() {
        return priceBase;
    }

    public int getType() {
        return type;
    }
}
