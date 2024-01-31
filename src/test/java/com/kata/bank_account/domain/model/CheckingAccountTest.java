package com.kata.bank_account.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CheckingAccountTest {
    @Test
    void should_have_an_id() {
        BankAccount bankAccount = new CheckingAccount();
        assertNotNull(bankAccount.getId());
    }

    @Test
    void should_have_a_balance() {
        BankAccount bankAccount = new CheckingAccount();
        assertEquals(BigDecimal.ZERO, bankAccount.getBalance());
    }

    @Test
    void can_have_an_overdraft() {
        CheckingAccount bankAccount = new CheckingAccount();
        assertFalse(bankAccount.hasAllowedOverdraft());
    }
}
