package com.kata.bank.domain.model

import com.kata.bank.domain.exception.AboveOverdraftException
import com.kata.bank.domain.exception.KInsufficientFundsException
import java.lang.Math.abs
import java.util.*

open class BankAccount() {

    lateinit var id: UUID
    private var transactions = Transactions()
     var overDraft: Long = 0
         private set

    constructor(overDraft: Long) : this() {
        this.overDraft = overDraft
    }

    fun balance(): Long {
        return transactions.total()
    }

    open fun deposit(amount: Long) {
        transactions.add(Transaction(amount))
    }

    fun withdraw(amount: Long) {
        if (sufficientFundToWithdraw(amount) || canOverdraft(amount)) {
            transactions.add(Transaction(-amount))
            return
        }
        when (overDraftAllowed()) {
            true -> throw AboveOverdraftException()
            else -> throw KInsufficientFundsException()
        }
    }

    private fun overDraftAllowed() = overDraft != 0.toLong()

    private fun canOverdraft(amount: Long) = abs(balance() - amount) <= overDraft

    private fun sufficientFundToWithdraw(amount: Long) = amount <= balance()
}