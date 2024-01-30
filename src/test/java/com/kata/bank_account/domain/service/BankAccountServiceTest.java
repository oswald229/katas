package com.kata.bank_account.domain.service;

import com.kata.bank_account.domain.exceptions.AboveOverdraftException;
import com.kata.bank_account.domain.exceptions.InsufficientFundsException;
import com.kata.bank_account.domain.model.BankAccount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankAccountServiceTest {

    private final BankAccountService bankAccountService = new BankAccountService();

    @Test
    void should_make_a_deposit() {
        BankAccount bankAccount = new BankAccount();
        BigDecimal depositAmount = BigDecimal.TEN;
        BigDecimal newBalance = bankAccountService.deposit(depositAmount, bankAccount);

        assertEquals(newBalance, depositAmount);

    }

    @Test
    void should_make_a_withdraw() {
        BankAccount bankAccount = new BankAccount();
        BigDecimal depositAmount = BigDecimal.TEN;
        bankAccountService.deposit(depositAmount, bankAccount);

        BigDecimal withdrawAmount = BigDecimal.valueOf(5);
        BigDecimal newBalance = bankAccountService.withdraw(withdrawAmount, bankAccount);

        assertEquals(withdrawAmount, newBalance);
    }

    @Test
    void should_not_allow_withdraw_with_insufficient_amount() {
        BankAccount bankAccount = new BankAccount();
        BigDecimal withdrawAmount = BigDecimal.TEN;
        assertThrows(InsufficientFundsException.class, () -> bankAccountService.withdraw(withdrawAmount, bankAccount));
    }

    @Test
    void should_allow_over_draft() {
        BankAccount bankAccount = new BankAccount();
        BigDecimal allowedOverdraft = bankAccountService.allowOverdraftOf(BigDecimal.TEN, bankAccount);

        assertEquals(allowedOverdraft, bankAccount.getAllowedOverDraft());
    }

    @Test
    void should_allow_withdraw_within_overdraft() {
        BankAccount bankAccount = new BankAccount();
        bankAccountService.allowOverdraftOf(BigDecimal.TEN, bankAccount);
        BigDecimal withdrawAmount = BigDecimal.valueOf(5);

        assertEquals(BigDecimal.valueOf(5).negate(), bankAccountService.withdraw(withdrawAmount, bankAccount));
    }

    @Test
    void should_not_allow_withdraw_without_overdraft() {
        BankAccount bankAccount = new BankAccount();
        BigDecimal withdrawAmount = BigDecimal.valueOf(5);

        assertThrows(InsufficientFundsException.class, () -> bankAccountService.withdraw(withdrawAmount, bankAccount));

    }

    @Test
    void should_throw_when_above_overdraft() {
        BankAccount bankAccount = new BankAccount();
        bankAccountService.allowOverdraftOf(BigDecimal.TEN, bankAccount);
        BigDecimal withdrawAmount = BigDecimal.valueOf(20);

        assertThrows(AboveOverdraftException.class, () -> bankAccountService.withdraw(withdrawAmount, bankAccount));

    }
}
