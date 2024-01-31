package com.kata.bank_account.domain.model;

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
}