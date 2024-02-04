package com.kata.bank_account.domain.kernel.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    @Test
    void should_update_transactions_list() {
        Transaction transaction = new Transaction(BigDecimal.TEN, LocalDateTime.now());
        BankAccount bankAccount = new CheckingAccount();
        bankAccount.addTransaction(transaction);

        assertEquals(1, bankAccount.getTransactions().size());

    }
}