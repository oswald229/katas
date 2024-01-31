package com.kata.bank_account.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transaction(BigDecimal amount, LocalDateTime transactionDateTime) {
}
