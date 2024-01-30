package com.kata.bank_account.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BankAccountTest {
    @Test
    void should_have_an_id() {
        BankAccount bankAccount = new BankAccount();
        assertNotNull(bankAccount.getId());
    }

    @Test
    void should_have_a_balance() {
        BankAccount bankAccount = new BankAccount();
        assertEquals(BigDecimal.ZERO, bankAccount.getBalance());
    }

    @Test
    void can_have_an_overdraft() {
        BankAccount bankAccount = new BankAccount();

        assertEquals(false, bankAccount.hasAllowedOverdraft());
    }
}
