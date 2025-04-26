package com.kata.pokerhands;

class PlainTextHandRenderer implements HandRenderer<String> {
    @Override
    public String render(HandWinner handWinner) {
        return handWinner.hand().toString();
    }
}
