package com.kata.bank_account.domain.entities;

import com.kata.bank_account.domain.exception.InsufficientFundsException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BankAccountTest {

    @Test
    void should_have_an_id() {
        BankAccount bankAccount = new BankAccount();

        assertThat(bankAccount.id()).isNotNull();
    }

    @Test
    void should_have_a_balance() {
        BankAccount bankAccount = new BankAccount();

        assertThat(bankAccount.balance()).isZero();
    }

    @Test
    void should_have_sufficient_funds_for_withdraw() {
        var bankAccount = new BankAccount();

        var withDrawnAmount = BigDecimal.TEN;

        assertThatThrownBy(() -> bankAccount.withdraw(withDrawnAmount))
                .isInstanceOf(InsufficientFundsException.class);
    }

    @Test
    void should_withdraw_with_sufficient_funds() {
        var bankAccount = new BankAccount();
        bankAccount.deposit(BigDecimal.TEN);

        var withDrawnAmount = BigDecimal.TEN;
        bankAccount.withdraw(withDrawnAmount);

        assertThat(bankAccount.balance()).isZero();
    }

    @Test
    void should_allow_withdraw_within_overdraft() {
        var overdraft = BigDecimal.valueOf(50);
        BankAccount bankAccount = new BankAccount(overdraft);

        bankAccount.withdraw(BigDecimal.TEN);

        assertThat(bankAccount.balance()).isEqualTo(BigDecimal.valueOf(-10));

    }
}
