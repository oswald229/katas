package com.kata.bank_account.domain.shared.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface FundTransaction {
    BigDecimal amount();

    LocalDateTime time();
}
