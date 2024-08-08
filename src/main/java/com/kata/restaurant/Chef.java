package com.kata.restaurant;

public class Chef {

    private final String name;

    public Chef(String name) {
        this.name = name;
    }

    public Meal prepare(Order order) {
        String mealName = order.item().name();
        System.out.printf("Chef " + name + " is cooking your %s...%n", mealName);
        return new Meal(mealName);
    }
}
