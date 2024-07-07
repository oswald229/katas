package com.kata.bank_account.domain.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BankTransaction(BigDecimal amount, LocalDateTime time) {

    public BankTransaction(BigDecimal amount) {
        this(amount, LocalDateTime.now());
    }
}