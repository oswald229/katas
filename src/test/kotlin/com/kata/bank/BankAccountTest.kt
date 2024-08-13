package com.kata.bank

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class BankAccountTest {

    @Test
    fun should_have_required_fields() {
        val bankAccount = BankAccount()

        assertThat(bankAccount).hasFieldOrProperty("id")
        assertThat(bankAccount.balance()).isEqualTo(0)
    }

    @Test
    fun should_make_deposit() {
        val bankAccount = BankAccount()

        val amount: Long = 10_000

        bankAccount.deposit(amount)

        assertThat(bankAccount.balance()).isEqualTo(amount)
    }

    @Test
    fun should_make_withdraw() {
        val bankAccount = BankAccount()
        val amount: Long = 10_000
        bankAccount.deposit(amount)

        bankAccount.withdraw(5_000);

        assertThat(bankAccount.balance()).isEqualTo(5_000)
    }

    @Test
    fun should_throw_when_not_enough_funds() {
        val bankAccount = BankAccount()

        assertThatThrownBy { bankAccount.withdraw(5_000) }
            .isInstanceOf(KInsufficientFundsException::class.java)
    }

    @Test
    fun should_allow_withdraw_with_overdraft() {
        val overDraft: Long = 1000
        val bankAccount = BankAccount(overDraft)

        bankAccount.withdraw(1000)

        assertThat(bankAccount.balance()).isEqualTo(-1000)

    }

    @Test
    fun should_throw_when_above_overdraft() {
        val overDraft: Long = 1000
        val bankAccount = BankAccount(overDraft)

        assertThatThrownBy { bankAccount.withdraw(2_000) }
            .isInstanceOf(AboveOverdraftException::class.java)
    }

}

