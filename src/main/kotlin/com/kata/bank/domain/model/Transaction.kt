package com.kata.bank.domain.model

import java.time.LocalDateTime

data class Transaction(val amount: Long, val time: LocalDateTime) {
    constructor(amount: Long) : this(amount, LocalDateTime.now())
}

class Transactions {
    private var transactions: MutableList<Transaction> = mutableListOf()

    fun add(transaction: Transaction) {
        transactions.add(transaction)
    }

    fun total(): Long {
        return transactions
            .stream()
            .mapToLong(Transaction::amount)
            .sum()
    }
}