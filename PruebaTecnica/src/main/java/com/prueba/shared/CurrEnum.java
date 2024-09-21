package com.prueba.shared;

public enum CurrEnum {
	EUR(0), DOLLAR(1), YEN(2);
	
	private final int value;

    private CurrEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
