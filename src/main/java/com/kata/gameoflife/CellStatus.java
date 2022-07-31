package com.kata.gameoflife;

public enum CellStatus {
    ALIVE("*"), DEAD(".");

    private final String value;
    CellStatus(String value){

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
