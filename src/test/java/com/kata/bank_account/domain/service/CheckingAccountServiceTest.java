package com.kata.bank_account.domain.service;

import com.kata.bank_account.domain.exceptions.AboveOverdraftException;
import com.kata.bank_account.domain.exceptions.InsufficientFundsException;
import com.kata.bank_account.domain.model.CheckingAccount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CheckingAccountServiceTest {

    private final CheckingAccountService checkingAccountService = new CheckingAccountService();

    @Test
    void should_make_a_deposit() {
        CheckingAccount bankAccount = new CheckingAccount();
        BigDecimal depositAmount = BigDecimal.TEN;
        BigDecimal newBalance = checkingAccountService.deposit(depositAmount, bankAccount);

        assertEquals(newBalance, depositAmount);

    }

    @Test
    void should_make_a_withdraw() {
        CheckingAccount bankAccount = new CheckingAccount();
        BigDecimal depositAmount = BigDecimal.TEN;
        bankAccount.increaseBalance(depositAmount);

        BigDecimal withdrawAmount = BigDecimal.valueOf(5);
        BigDecimal newBalance = checkingAccountService.withdraw(withdrawAmount, bankAccount);

        assertEquals(withdrawAmount, newBalance);
    }

    @Test
    void should_not_allow_withdraw_with_insufficient_amount() {
        CheckingAccount bankAccount = new CheckingAccount();
        BigDecimal withdrawAmount = BigDecimal.TEN;
        assertThrows(InsufficientFundsException.class, () -> checkingAccountService.withdraw(withdrawAmount, bankAccount));
    }

    @Test
    void should_allow_over_draft() {
        CheckingAccount bankAccount = new CheckingAccount();
        BigDecimal allowedOverdraft = checkingAccountService.allowOverdraftOf(BigDecimal.TEN, bankAccount);

        assertEquals(allowedOverdraft, bankAccount.getAllowedOverDraft());
    }

    @Test
    void should_allow_withdraw_within_overdraft() {
        CheckingAccount bankAccount = new CheckingAccount();
        checkingAccountService.allowOverdraftOf(BigDecimal.TEN, bankAccount);
        BigDecimal withdrawAmount = BigDecimal.valueOf(5);

        assertEquals(BigDecimal.valueOf(5).negate(), checkingAccountService.withdraw(withdrawAmount, bankAccount));
    }

    @Test
    void should_not_allow_withdraw_without_overdraft() {
        CheckingAccount bankAccount = new CheckingAccount();
        BigDecimal withdrawAmount = BigDecimal.valueOf(5);

        assertThrows(InsufficientFundsException.class, () -> checkingAccountService.withdraw(withdrawAmount, bankAccount));

    }

    @Test
    void should_throw_when_above_overdraft() {
        CheckingAccount bankAccount = new CheckingAccount();
        checkingAccountService.allowOverdraftOf(BigDecimal.TEN, bankAccount);
        BigDecimal withdrawAmount = BigDecimal.valueOf(20);

        assertThrows(AboveOverdraftException.class, () -> checkingAccountService.withdraw(withdrawAmount, bankAccount));

    }
}
