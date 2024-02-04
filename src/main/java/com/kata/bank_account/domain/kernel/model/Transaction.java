package com.kata.bank_account.domain.kernel.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transaction(BigDecimal amount, LocalDateTime transactionDateTime) {
}
