package com.kata.bank_account.domain.entities;

import com.kata.bank_account.domain.exception.InsufficientFundsException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CheckingAccountTest {

    @Test
    void should_have_an_id() {
        CheckingAccount checkingAccount = new CheckingAccount();

        assertThat(checkingAccount.id()).isNotNull();
    }

    @Test
    void should_have_a_balance() {
        CheckingAccount checkingAccount = new CheckingAccount();

        assertThat(checkingAccount.balance()).isZero();
    }

    @Test
    void should_have_sufficient_funds_for_withdraw() {
        var bankAccount = new CheckingAccount();

        var withDrawnAmount = BigDecimal.TEN;

        assertThatThrownBy(() -> bankAccount.withdraw(withDrawnAmount))
                .isInstanceOf(InsufficientFundsException.class);
    }

    @Test
    void should_withdraw_with_sufficient_funds() {
        var bankAccount = new CheckingAccount();
        bankAccount.deposit(BigDecimal.TEN);

        var withDrawnAmount = BigDecimal.TEN;
        bankAccount.withdraw(withDrawnAmount);

        assertThat(bankAccount.balance()).isZero();
    }

    @Test
    void should_allow_withdraw_within_overdraft() {
        var overdraft = BigDecimal.valueOf(50);
        CheckingAccount checkingAccount = new CheckingAccount(overdraft);

        checkingAccount.withdraw(BigDecimal.TEN);

        assertThat(checkingAccount.balance()).isEqualTo(BigDecimal.valueOf(-10));

    }

    @Test
    void should_throw_on_withdraw_greater_than_over_draft() {
        var overdraft = BigDecimal.valueOf(50);
        CheckingAccount checkingAccount = new CheckingAccount(overdraft);

        BigDecimal withDrawAmount = BigDecimal.valueOf(100);

        assertThatThrownBy(() -> checkingAccount.withdraw(withDrawAmount))
                .isInstanceOf(InsufficientFundsException.class);

    }
}
