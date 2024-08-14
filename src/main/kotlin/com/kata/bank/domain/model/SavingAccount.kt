package com.kata.bank.domain.model

import com.kata.bank.domain.exception.AboveCeilingException

open class SavingAccount(private val ceiling: Long) : BankAccount() {

    override fun deposit(amount: Long) {
        if (aboveCeilingOnDeposit(amount)) {
            throw AboveCeilingException()
        }
        super.deposit(amount)
    }

    private fun aboveCeilingOnDeposit(amount: Long) = (balance() + amount) > ceiling
    override fun type(): AccountType {
        return AccountType.SAVING
    }
}
