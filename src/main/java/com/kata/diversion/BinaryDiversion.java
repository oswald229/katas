package com.kata.diversion;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

public class BinaryDiversion {

    public List<String> getBinaries(int n) {

        int max = Integer.parseInt("1".repeat(n), 2);

        UnaryOperator<String> completeWithZeros = s ->
                "0".repeat(n - s.length())
                        .concat(s);

        return IntStream.range(0, max + 1)
                .mapToObj(Integer::toBinaryString)
                .map(completeWithZeros).toList();
    }

    public boolean hasTwoAdjacent1Bits(String binaryString) {

        List<String> binaryStringList = Arrays.asList(binaryString.split(""));

        for (int i = 0, j = i + 1; j < binaryStringList.size(); i++, j++) {

            if (binaryStringList.get(i).equals("1")
                    && binaryStringList.get(j).equals("1")) {
                return true;
            }

        }

        return false;

    }

    public int getDiversionCriteria(int n) {

        return (int) getBinaries(n)
                .stream()
                .filter(s -> !hasTwoAdjacent1Bits(s))
                .count();

    }
}
