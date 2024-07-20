package com.kata.bank_account.domain.shared.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Deposit(BigDecimal amount, LocalDateTime time) implements FundTransaction {
}
