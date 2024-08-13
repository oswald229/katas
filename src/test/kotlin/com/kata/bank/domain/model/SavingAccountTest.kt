package com.kata.bank.domain.model


import com.kata.bank.domain.exception.AboveCeilingException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class SavingAccountTest {

    @Test
    fun should_not_have_overdraft() {
        val ceiling: Long = 100
        val savingAccount = SavingAccount(ceiling)

        assertThat(savingAccount.overDraft).isEqualTo(0)
    }

    @Test
    fun should_have_ceiling() {
        val ceiling: Long = 100
        val savingAccount = SavingAccount(ceiling)

        assertThatThrownBy { savingAccount.deposit(200) }
            .isInstanceOf(AboveCeilingException::class.java)
    }

    @Test
    fun should_allow_deposit_within_ceiling() {
        val ceiling: Long = 100
        val savingAccount = SavingAccount(ceiling)

        savingAccount.deposit(50)

        assertThat(savingAccount.balance()).isEqualTo(50)
    }
}