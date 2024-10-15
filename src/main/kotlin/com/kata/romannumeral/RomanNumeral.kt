package com.kata.romannumeral

import java.util.*
import kotlin.math.abs

class RomanNumeral {
    private val romanNumeralDefinition: TreeMap<Int, String> = TreeMap(
        mapOf(
            Pair(1, "I"),
            Pair(5, "V"),
            Pair(10, "X"),
            Pair(50, "L"),
            Pair(100, "C"),
            Pair(500, "D"),
            Pair(1000, "M"),
            Pair(9999999, "!!!!!")
        )
    )

    fun toRoman(number: Int): String? {
        if (number == 0) {
            return ""
        }
        if (number == 1) {
            return romanNumeralDefinition[number]
        }

        val closestInferiorRomanNumeralIdx = getClosestInferiorRomanNumeralIdx(number)

        if (closestInferiorRomanNumeralIdx + 1 < romanNumeralDefinition.size) {
            val nextSuperiorRomanNumeral = romanNumeralKeys()[closestInferiorRomanNumeralIdx + 1]

            val deltaWithNextSuperiorRomanNumeral = abs(number - nextSuperiorRomanNumeral)
            if (isRomanNumeralDefinition(deltaWithNextSuperiorRomanNumeral)
                &&
                deltaWithNextSuperiorRomanNumeral != number
            ) {
                return romanNumeralDefinition[deltaWithNextSuperiorRomanNumeral] + romanNumeralDefinition[nextSuperiorRomanNumeral]
            }
        }

        val closestInferiorRomanNumeral = romanNumeralKeys()[closestInferiorRomanNumeralIdx]
        return romanNumeralDefinition[closestInferiorRomanNumeral] + toRoman(number - closestInferiorRomanNumeral)
    }

    private fun getClosestInferiorRomanNumeralIdx(number: Int): Int {
        return romanNumeralDefinition
            .filter { entry -> number % entry.key != number }
            .toList().size - 1
    }

    private fun romanNumeralKeys() = romanNumeralDefinition.keys.toList()

    private fun isRomanNumeralDefinition(number: Int) = romanNumeralDefinition.containsKey(number)

}
