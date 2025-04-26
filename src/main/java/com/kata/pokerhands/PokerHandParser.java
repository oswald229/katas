package com.kata.pokerhands;

import java.util.List;

public interface PokerHandParser {
    List<Card> parse(String hand);
}
