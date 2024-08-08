package com.kata.restaurant;

import java.util.List;

public class OrderProcessor {

    static final List<MenuItem> menu = List.of(
            new MenuItem("Pizza"),
            new MenuItem("Cheesecake"),
            new MenuItem("Salad"));

    private final Chef chef = new Chef("John");

    void process(Order order) {
        if (!menu.contains(order.item())) {
            throw new RuntimeException("Item not in restaurant menu.");
        }
        System.out.println("Order validated.");
        var meal = chef.prepare(order);
        deliver(order, meal);
    }


    private void deliver(Order order, Meal meal) {
        System.out.println("Hi I'm Bob and I'm delivering your %s".formatted(meal.name()));
        System.out.println("Delivered to table %s!".formatted(order.table()));
    }

}
