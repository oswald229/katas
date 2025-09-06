package com.kata.pokerhands.game.rules;

import com.kata.pokerhands.game.model.PokerHandEnum;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PokerHandComparator implements Comparator<PokerHandEnum> {
    @Override
    public int compare(PokerHandEnum hand1, PokerHandEnum hand2) {

        List<PokerHandEnum> pokerHandEnums = Arrays.stream(PokerHandEnum.values()).toList();

        PokerHandEnum higherHand = pokerHandEnums.indexOf(hand1) > pokerHandEnums.indexOf(hand2) ? hand1 : hand2;

        if (higherHand.equals(hand1)) {
            return 1;
        }
        return -1;
    }
}
