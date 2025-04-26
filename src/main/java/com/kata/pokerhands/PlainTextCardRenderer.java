package com.kata.pokerhands;

class PlainTextCardRenderer implements CardRenderer<String, String> {
    @Override
    public String render(String card) {
        return switch (card) {
            case "A" -> "Ace";
            case "J" -> "Jack";
            case "Q" -> "Queen";
            case "K" -> "King";
            default -> card;
        };
    }
}
