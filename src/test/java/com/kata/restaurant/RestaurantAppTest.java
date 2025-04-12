package com.kata.restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;

@Disabled
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

        String expected = """
                Order validated.
                Chef John is cooking your Pizza...
                Hi I'm Bob and I'm delivering your Pizza
                Delivered to table 1!""";

        assertThat(outputStreamCaptor.toString()).isEqualToIgnoringNewLines(expected);

    }

    @Test
    void should_throw_when_item_not_in_menu() {
        assertThatThrownBy(() -> new OrderProcessor().process(new Order("Paela", 5)))
                .hasMessage("Item not in restaurant menu.");
    }

    @Disabled
    void should_process_order_with_custom_setup() {
        var plate = "Salad";
        var deliveryAddress = "123 Food Road";
        var deliveringEmployee = "Tom";

        fail("To Implement");
        String expected = """
                Order validated.
                Chef Alice is cooking your Salad...
                Hi I'm Tom and I'm delivering your Salad
                Delivered to 123 Food Road!""";
        assertThat(outputStreamCaptor.toString()).containsIgnoringNewLines(expected);
    }
}
