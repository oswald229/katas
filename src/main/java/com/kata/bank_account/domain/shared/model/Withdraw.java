package com.kata.bank_account.domain.shared.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Withdraw(BigDecimal amount, LocalDateTime time, UUID id) implements FundTransaction {
    public Withdraw {
        amount = amount.abs().negate();
    }
    public Withdraw(BigDecimal amount){
        this(amount, LocalDateTime.now(), UUID.randomUUID());
    }
}
