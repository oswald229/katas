package com.kata.bank.domain.model

class InMemoryBankAccount : BankAccount() {
    fun mockTransactions(transactions: Transactions) {
        this.transactions = transactions
    }
}