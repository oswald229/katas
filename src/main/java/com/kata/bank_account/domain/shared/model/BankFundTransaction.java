package com.kata.bank_account.domain.shared.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record BankFundTransaction(BigDecimal amount, LocalDateTime time, UUID id) implements FundTransaction {

    public BankFundTransaction(BigDecimal amount) {
        this(amount, LocalDateTime.now(), UUID.randomUUID());
    }

    public BankFundTransaction(BigDecimal amount, LocalDateTime time) {
        this(amount, time, UUID.randomUUID());
    }
}
