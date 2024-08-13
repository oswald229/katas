package com.kata.bank.domain.model

import com.kata.bank.domain.exception.AboveCeilingException

class SavingAccount(val ceiling: Long) : BankAccount() {

    override fun deposit(amount: Long) {
        if (depositAboveCeiling(amount)) {
            throw AboveCeilingException()
        }
        super.deposit(amount)
    }

    private fun depositAboveCeiling(amount: Long) = (balance() + amount) > ceiling
}
