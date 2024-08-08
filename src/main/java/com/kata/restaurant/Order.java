package com.kata.restaurant;

public record Order(MenuItem item, int table) {
    public Order(String item, int table) {
        this(new MenuItem(item), table);
    }

}
