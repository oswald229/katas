package com.kata.bank_account.domain.shared.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Deposit(BigDecimal amount, LocalDateTime time, UUID id) implements FundTransaction {
    public Deposit(BigDecimal amount) {
        this(amount, LocalDateTime.now());
    }

    public Deposit(BigDecimal amount, LocalDateTime time) {
        this(amount.abs(), time, UUID.randomUUID());
    }
}
