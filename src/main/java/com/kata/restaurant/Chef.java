package com.kata.restaurant;

public class Chef {

    private final String name;

    public Chef(String name) {
        this.name = name;
    }

    public Meal prepare(Order order) {
        String mealName = order.item().name();
        System.out.println("Chef " + name + " is cooking your %s...".formatted(mealName));
        return new Meal(mealName);
    }
}
