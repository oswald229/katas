package com.kata.bank

import java.time.LocalDateTime

data class Transaction(val amount: Long, val time: LocalDateTime) {
    constructor(amount: Long) : this(amount, LocalDateTime.now())
}

class Transactions {
    var transactions: MutableList<Transaction> = mutableListOf()

    fun add(transaction: Transaction) {
        transactions.add(transaction)
    }

    fun total(): Long {
        var total: Long = 0
        for (tr in transactions) {
            total += tr.amount
        }
        return total
    }
}