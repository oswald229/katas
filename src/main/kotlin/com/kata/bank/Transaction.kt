package com.kata.bank

data class Transaction(val amount: Long)

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