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

    private fun getAll(): List<Transaction> {
        return transactions.toList()
    }

    fun totalOn(dateTime: LocalDateTime): Long {
        return getAll()
            .stream()
            .filter { transaction -> transaction.time.isEqual(dateTime) || transaction.time.isBefore(dateTime) }
            .mapToLong { transaction -> transaction.amount }
            .sum()
    }

    fun occurredWithin(
        start: LocalDateTime, end: LocalDateTime
    ): List<Transaction> {
        return getAll()
            .filter { transaction: Transaction -> occurredWithin(start, end, transaction.time) }
            .sortedBy { transaction -> transaction.time }
            .toList()
    }


    private fun occurredWithin(
        start: LocalDateTime, end: LocalDateTime, target: LocalDateTime
    ): Boolean {
        return ((target.isEqual(end) || target.isBefore(end))
                && (target.isAfter(start) || target.isEqual(start)))
    }
}