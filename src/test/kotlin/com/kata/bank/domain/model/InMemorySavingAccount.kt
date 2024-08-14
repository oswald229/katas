package com.kata.bank.domain.model

class InMemorySavingAccount : SavingAccount(0) {
    fun mockTransactions(transactions: Transactions) {
        this.transactions = transactions
    }

}