package com.kata.bank_account.domain.service;

import com.kata.bank_account.domain.exceptions.AboveSavingLimitException;
import com.kata.bank_account.domain.model.SavingAccount;
import com.kata.bank_account.domain.model.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SavingAccountServiceTest {


    private final SavingAccountService savingAccountService = new SavingAccountService();

    @Test
    void should_not_allow_deposit_above_limit() {

        BigDecimal limit = BigDecimal.TEN;
        SavingAccount savingAccount = new SavingAccount(limit);
        BigDecimal depositAmount = limit.multiply(BigDecimal.TEN);

        assertThrows(AboveSavingLimitException.class,
                () -> savingAccountService.deposit(depositAmount, savingAccount));

    }

    @Test
    void should_allow_deposit_within_saving_limit() {
        BigDecimal limit = BigDecimal.TEN;
        SavingAccount savingAccount = new SavingAccount(limit);
        BigDecimal depositAmount = BigDecimal.TEN;
        BigDecimal newBalance = savingAccountService.deposit(depositAmount, savingAccount);

        assertEquals(newBalance, depositAmount);

    }

    @Test
    void should_make_a_withdraw() {
        SavingAccount savingAccount = new SavingAccount(BigDecimal.TEN);
        BigDecimal depositAmount = BigDecimal.TEN;
        savingAccount.addTransaction(new Transaction(depositAmount, LocalDateTime.now()));

        BigDecimal withdrawAmount = BigDecimal.valueOf(5);
        BigDecimal newBalance = savingAccountService.withdraw(withdrawAmount, savingAccount);

        assertEquals(withdrawAmount, newBalance);
    }
}
