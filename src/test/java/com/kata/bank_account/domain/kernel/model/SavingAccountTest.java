package com.kata.bank_account.domain.kernel.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SavingAccountTest {

    @Test
    void should_have_a_ceil_limit() {
        BigDecimal limit = BigDecimal.TEN;
        SavingAccount savingAccount = new SavingAccount(limit);

        assertEquals(limit, savingAccount.getLimit());
    }

    @Test
    void should_have_the_right_type() {
        BankAccount bankAccount = new SavingAccount(BigDecimal.TEN);

        Assertions.assertEquals(BankAccountType.SAVING, bankAccount.getType());
    }
}