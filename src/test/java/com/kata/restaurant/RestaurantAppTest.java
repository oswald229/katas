package com.kata.restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;

class RestaurantAppTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void should_process_order_with_default_setup() {
        var table = 1;
        var choice = "Pizza";
        var order = new Order(choice, table);

        new OrderProcessor().process(order);

        assertThat(outputStreamCaptor.toString())
                .contains("Order validated.")
                .contains("Chef John is cooking your Pizza...")
                .contains("Hi I'm Bob and I'm delivering your Pizza")
                .contains("Delivered to table 1!")
        ;
    }

    @Test
    void should_throw_when_item_not_in_menu() {
        assertThatThrownBy(() -> new OrderProcessor().process(new Order("Paela", 5)))
                .hasMessage("Item not in restaurant menu.");
    }

    @Test
    void should_process_order_with_custom_setup() {
        var plate = "Salad";
        var deliveryAddress = "123 Food Road";
        var deliveringEmployee = "Tom";

        fail("To Implement");

        assertThat(outputStreamCaptor.toString())
                .contains("Order validated.")
                .contains("Chef Alice is cooking your Salad...")
                .contains("Hi I'm Tom and I'm delivering your Salad")
                .contains("Delivered to 123 Food Road!")
        ;
    }
}