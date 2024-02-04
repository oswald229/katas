package com.kata.bank_account.domain.kernel.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionTest {
    @Test
    void should_have_required_data() {

        BigDecimal depositAmount = BigDecimal.TEN;
        LocalDateTime transactionDateTime = LocalDateTime.now();
        Transaction transaction = new Transaction(depositAmount, transactionDateTime);

        assertEquals(depositAmount, transaction.amount());
        assertEquals(transactionDateTime, transaction.transactionDateTime());
    }
}
