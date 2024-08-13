package com.kata.bank

import java.util.*

class BankAccount {
    lateinit var id: UUID
    private var transactions: Transactions = Transactions()

    fun balance(): Long {
        return transactions.total()
    }

    fun deposit(amount: Long) {
        transactions.add(Transaction(amount))
    }

    fun withdraw(amount: Long) {
        if (amount > balance()) {
            throw KInsufficientFundsException()
        }
        transactions.add(Transaction(-amount))
    }
}